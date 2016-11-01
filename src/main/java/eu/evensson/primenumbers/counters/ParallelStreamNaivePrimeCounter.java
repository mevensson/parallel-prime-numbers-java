package eu.evensson.primenumbers.counters;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

public class ParallelStreamNaivePrimeCounter implements PrimeCounter {

	private final ForkJoinPool pool;

	public ParallelStreamNaivePrimeCounter(final ForkJoinPool pool) {
		this.pool = pool;
	}

	@Override
	public long countPrimes(final long maxPrime) {
		if (maxPrime < 2) {
			return 0;
		}

		try {
			return pool.submit(() ->
				LongStream
					.range(2, maxPrime + 1)
					.parallel()
					.filter((e) -> isPrime(e))
					.count()
				).get();
		} catch (InterruptedException | ExecutionException e) {
			throw new RuntimeException(e);
		}
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
