package eu.evensson.primenumbers.counters;

import java.util.function.Function;

import eu.evensson.primenumbers.counters.primelists.PrimeList;

public class SievePrimeCounter implements PrimeCounter {

	private final Function<Long, PrimeList> primeListFactory;

	public SievePrimeCounter(final Function<Long, PrimeList> primeArrayFactory) {
		this.primeListFactory = primeArrayFactory;
	}

	@Override
	public long countPrimes(final long maxPrime) {
		if (maxPrime < 2) {
			return 0;
		}

		final PrimeList primeList = primeListFactory.apply(maxPrime);
		for (long i = 2; i * i <= maxPrime; ++i) {
			if (primeList.check(i)) {
				for (long j = i * i; j <= maxPrime; j += i) {
					primeList.cross(j);
				}
			}
		}
		return primeList.countSet();
	}
}
