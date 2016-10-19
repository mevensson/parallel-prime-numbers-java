package eu.evensson.primenumbers.counters;

import java.util.stream.LongStream;

public class StreamNaivePrimeCounter implements PrimeCounter {

	@Override
	public long countPrimes(final long maxPrime) {
		if (maxPrime < 2) {
			return 0;
		}

		return LongStream.range(2, maxPrime + 1).filter((e) -> isPrime(e)).count();
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
