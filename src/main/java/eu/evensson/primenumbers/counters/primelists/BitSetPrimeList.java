package eu.evensson.primenumbers.counters.primelists;

import java.util.BitSet;

public class BitSetPrimeList implements PrimeList {
	private final BitSet bitSet;

	public BitSetPrimeList(final long maxPrime) {
		bitSet = new BitSet((int)maxPrime - 1);
		bitSet.set(0, (int)(maxPrime - 1));
	}

	@Override
	public boolean check(final long n) {
		return bitSet.get((int)(n - 2));
	}

	@Override
	public void cross(final long n) {
		bitSet.clear((int)(n - 2));
	}

	@Override
	public long countSet() {
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
