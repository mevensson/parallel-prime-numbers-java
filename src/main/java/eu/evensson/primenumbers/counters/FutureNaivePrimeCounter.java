package eu.evensson.primenumbers.counters;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class FutureNaivePrimeCounter implements PrimeCounter {

	private final ExecutorService pool;

	public FutureNaivePrimeCounter(final ExecutorService pool) {
		this.pool = pool;
	}

	@Override
	public long countPrimes(final long maxPrime) {
		if (maxPrime < 2) {
			return 0;
		}

		final List<Future<Boolean>> futures = new ArrayList<>();
		for (long i = 2; i <= maxPrime; ++i) {
			final long number = i;
			futures.add(pool.submit(() -> isPrime(number)));
		}

		long numPrimes = 0;
		for (final Future<Boolean> future : futures) {
			try {
				if (future.get()) {
					numPrimes += 1;
				}
			} catch (InterruptedException | ExecutionException e) {
				throw new RuntimeException(e);
			}
		}
		return numPrimes;
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
