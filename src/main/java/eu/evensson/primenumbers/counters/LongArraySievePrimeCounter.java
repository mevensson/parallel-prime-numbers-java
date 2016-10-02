package eu.evensson.primenumbers.counters;

public class LongArraySievePrimeCounter implements PrimeCounter {

	static class PrimeArray {
		private final long[] longArray;

		public PrimeArray(final long maxPrime) {
			longArray = new long[arrayIndex(maxPrime) + 1];
			for (int i = 0; i < longArray.length - 1; ++i) {
				longArray[i] = 0xffff_ffff_ffff_ffffL;
			}
			for (int i = 0; i <= bitIndex(maxPrime); ++i) {
				longArray[longArray.length - 1] |= (1L << i);
			}
		}

		static int arrayIndex(final long n) {
			return (int) ((n - 2) / 64);
		}

		static int bitIndex(final long n) {
			return (int) ((n -2) % 64);
		}

		void cross(final long n) {
			longArray[arrayIndex(n)] &= ~(1L << bitIndex(n));
		}

		boolean check(final long n) {
			return (longArray[arrayIndex(n)] & 1L << bitIndex(n)) != 0;
		}

		long countSet() {
			long numPrimes = 0;
			for (int i = 0; i < longArray.length; ++i) {
				numPrimes += Long.bitCount(longArray[i]);
			}
			return numPrimes;
		}

		@Override
		public String toString() {
			String result = "Set Numbers: ";
			for (long i = 0; i < longArray.length; ++i) {
				for (long j = 0; j < 64; ++j) {
					final long n = i * 64 + j + 2;
					if (check(n)) {
						result += Long.toString(n);
						result += " ";
					}
				}
			}
			return result;
		}
	}

	@Override
	public long countPrimes(final long maxPrime) {
		if (maxPrime < 2) {
			return 0;
		}

		final PrimeArray primeArray = new PrimeArray(maxPrime);
		for (long i = 2; i * i <= maxPrime; ++i) {
			if (primeArray.check(i)) {
				for (long j = i * i; j <= maxPrime; j += i) {
					primeArray.cross(j);
				}
			}
		}
		return primeArray.countSet();
	}
}
