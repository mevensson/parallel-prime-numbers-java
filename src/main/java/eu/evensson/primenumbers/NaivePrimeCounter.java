package eu.evensson.primenumbers;

public class NaivePrimeCounter implements PrimeCounter {

	@Override
	public long countPrimes(final long maxPrime) {
		if (maxPrime < 2) {
			return 0;
		}

		long numPrimes = 0;
		for (long i = 2; i <= maxPrime; ++i) {
			if (isPrime(i)) {
				numPrimes += 1;
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
