package eu.evensson.primenumbers.counters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;

import eu.evensson.primenumbers.counters.primelists.PrimeList;

public class FutureSievePrimeCounter implements PrimeCounter {

	private final ExecutorService pool;
	private final Function<Long, PrimeList> primeListFactory;

	public FutureSievePrimeCounter(
			final ExecutorService pool,
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
				crossNonPrimes(maxPrime, primeList, i);
			}
		}
		return primeList.countSet();
	}

	private void crossNonPrimes(
			final long maxPrime, final PrimeList primeList, final long prime) {
		final List<Callable<Void>> tasks = new ArrayList<>();
		for (long j = prime * prime; j <= maxPrime; j += prime) {
			final long nonPrime = j;
			tasks.add(() -> {
				primeList.cross(nonPrime);
				return null;
			});
		}
		try {
			pool.invokeAll(tasks);
		} catch (final InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
