package eu.evensson.primenumbers;

import java.util.BitSet;

public class BitSetSievePrimeCounter implements PrimeCounter {

	static class PrimeArray {
		private final BitSet bitSet;

		public PrimeArray(final long maxPrime) {
			bitSet = new BitSet((int)maxPrime - 1);
			bitSet.set(0, (int)(maxPrime - 1));
		}

		void cross(final long n) {
			bitSet.clear((int)(n - 2));
		}

		boolean check(final long n) {
			return bitSet.get((int)(n - 2));
		}

		long countSet() {
			long numPrimes = 0;
			for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
				numPrimes += 1;
			}
			return numPrimes;
		}

		@Override
		public String toString() {
			String result = "Size: " + bitSet.length() + ", Set Numbers: ";
			for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
				result += Integer.toString(i + 2);
				result += " ";
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
