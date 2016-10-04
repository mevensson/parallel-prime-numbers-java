package eu.evensson.primenumbers.counters;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinNaivePrimeCounter implements PrimeCounter {

	private static class CountPrimes extends RecursiveTask<Long> {
		private static final long serialVersionUID = 3251745589631719595L;

		private final long low;
		private final long high;

		public CountPrimes(final long low, final long high) {
			this.low = low;
			this.high = high;
		}

		@Override
		protected Long compute() {
			if (high == low) {
				if (isPrime(high)) {
					return 1l;
				}

				return 0l;
			}

			final long mid = low + (high - low) / 2;
			final CountPrimes left = new CountPrimes(low, mid);
			final CountPrimes right = new CountPrimes(mid + 1, high);
			left.fork();
			final long rightResult = right.compute();
			final long leftResult = left.join();
			return leftResult + rightResult;
		}

		private boolean isPrime(final long primeCandidate) {
			for (long i = 2; i < primeCandidate; ++i) {
				if (primeCandidate % i == 0) {
					return false;
				}
			}
			return true;
		}
	}

	private final ForkJoinPool pool;

	public ForkJoinNaivePrimeCounter(final ForkJoinPool pool) {
		this.pool = pool;
	}

	@Override
	public long countPrimes(final long maxPrime) {
		if (maxPrime < 2) {
			return 0;
		}

		return pool.invoke(new CountPrimes(2, maxPrime));
	}
}
