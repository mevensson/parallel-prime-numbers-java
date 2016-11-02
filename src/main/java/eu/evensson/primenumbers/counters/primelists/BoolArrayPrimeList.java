package eu.evensson.primenumbers.counters.primelists;

public class BoolArrayPrimeList implements PrimeList {

	private final boolean[] boolArray;

	public BoolArrayPrimeList(final long maxPrime) {
		boolArray = new boolean[(int) maxPrime - 2 + 1];
		for (long i = 0; i < boolArray.length; ++i) {
			boolArray[(int) i] = true;
		}
	}

	@Override
	public boolean check(final long n) {
		return boolArray[(int) n - 2];
	}

	@Override
	public void cross(final long n) {
		boolArray[(int) n - 2] = false;
	}

	@Override
	public long countSet() {
		long result = 0;
		for (final boolean b : boolArray) {
			if (b) {
				result += 1;
			}
		}
		return result;
	}

}
