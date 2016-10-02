package eu.evensson.primenumbers.counters.primelists;

public class LongArrayPrimeList implements PrimeList {
	private static int arrayIndex(final long n) {
		return (int) ((n - 2) / 64);
	}

	private static int bitIndex(final long n) {
		return (int) ((n -2) % 64);
	}

	private final long[] longArray;

	public LongArrayPrimeList(final long maxPrime) {
		longArray = new long[arrayIndex(maxPrime) + 1];
		for (int i = 0; i < longArray.length - 1; ++i) {
			longArray[i] = 0xffff_ffff_ffff_ffffL;
		}
		for (int i = 0; i <= bitIndex(maxPrime); ++i) {
			longArray[longArray.length - 1] |= (1L << i);
		}
	}

	@Override
	public boolean check(final long n) {
		return (longArray[arrayIndex(n)] & 1L << bitIndex(n)) != 0;
	}

	@Override
	public void cross(final long n) {
		longArray[arrayIndex(n)] &= ~(1L << bitIndex(n));
	}

	@Override
	public long countSet() {
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
