package eu.evensson.primenumbers.counters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

import eu.evensson.primenumbers.counters.primelists.PrimeList;

public class Future2SievePrimeCounter implements PrimeCounter {

	private final ForkJoinPool pool;
	private final Function<Long, PrimeList> primeListFactory;

	public Future2SievePrimeCounter(
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

		System.out.println("countPrimes(" + maxPrime + ")");
		final PrimeList primeList = primeListFactory.apply(maxPrime);
		for (long i = 2; i * i <= maxPrime; ++i) {
			if (primeList.check(i)) {
				crossNonPrimes1(primeList, i * i, maxPrime, i);
			}
		}
		final long countSet = primeList.countSet();
		System.out.println("primes = " + countSet);
		return countSet;
	}

	private void crossNonPrimes1(final PrimeList primeList, final long start,
			final long stop, final long step) {
		final long steps = (stop - start) / step;
		final long threads = pool.getParallelism();
		final List<Callable<Void>> tasks = new ArrayList<>();
		for (int i = 0; i < threads; ++i) {
			final long threadStart = start + step * (i * steps / threads);
			final long threadStop = start + step * ((i + 1) * steps / threads);
			tasks.add(() -> {
				crossNonPrimes2(primeList, threadStart, threadStop, step);
				return null;
			});
		}
		pool.invokeAll(tasks);
	}

	private void crossNonPrimes2(final PrimeList primeList, final long start,
			final long stop, final long step) {
		for (long j = start; j <= stop; j += step) {
			primeList.cross(j);
		}
	}
}
