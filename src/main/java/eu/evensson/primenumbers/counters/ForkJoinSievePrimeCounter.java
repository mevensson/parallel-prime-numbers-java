package eu.evensson.primenumbers.counters;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Function;

import eu.evensson.primenumbers.counters.primelists.PrimeList;

public class ForkJoinSievePrimeCounter implements PrimeCounter {

	private static class CrossNonPrimes extends RecursiveTask<Void> {
		private static final long serialVersionUID = -2483275139835153805L;

		private static long LIMIT = 10000;

		private final PrimeList primeList;
		private final long step;
		private final long low;
		private final long high;

		public CrossNonPrimes(final PrimeList primeList, final long step,
				final long low, final long high) {
			this.primeList = primeList;
			this.step = step;
			this.low = low;
			this.high = high;
		}

		@Override
		protected Void compute() {
			final long steps = (high - low) / step;
			if (steps < LIMIT) {
				for (long j = low; j <= high; j += step) {
					primeList.cross(j);
				}
			} else {
				final long mid = low + steps / 2 * step;
				final CrossNonPrimes left = new CrossNonPrimes(primeList, step, low, mid);
				final CrossNonPrimes right = new CrossNonPrimes(primeList, step, mid + step, high);
				left.fork();
				right.compute();
				left.join();
			}

			return null;
		}
	}

	private final ForkJoinPool pool;
	private final Function<Long, PrimeList> primeListFactory;

	public ForkJoinSievePrimeCounter(
			final ForkJoinPool pool,
			final Function<Long, PrimeList> primeArrayFactory) {
		this.pool = pool;
		this.primeListFactory = primeArrayFactory;
	}

	@Override
	public long countPrimes(final long maxPrime) {
		if (maxPrime < 2) {
			return 0;
		}

		final PrimeList primeList = primeListFactory.apply(maxPrime);
		for (long i = 2; i * i <= maxPrime; ++i) {
			if (primeList.check(i)) {
				pool.invoke(new CrossNonPrimes(primeList, i, i * i, maxPrime));
			}
		}
		return primeList.countSet();
	}
}
