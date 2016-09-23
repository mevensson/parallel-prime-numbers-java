package eu.evensson.primenumbers;

import java.util.LinkedList;
import java.util.List;

public class RememberingPrimeCounter implements PrimeCounter {

	@Override
	public long countPrimes(final long maxPrime) {
		if (maxPrime < 2) {
			return 0;
		}

		long numPrimes = 0;
		final List<Long> primes = new LinkedList<>();
		for (long i = 2; i <= maxPrime; ++i) {
			if (isPrime(i, primes)) {
				numPrimes += 1;
			}
		}
		return numPrimes;
	}

	private boolean isPrime(final long primeCandidate, final List<Long> primes) {
		for (final long prime : primes) {
			if (prime * prime > primeCandidate) {
				break;
			}
			if (primeCandidate % prime == 0) {
				return false;
			}
		}

		primes.add(primeCandidate);
		return true;
	}
}
