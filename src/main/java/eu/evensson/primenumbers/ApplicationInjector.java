package eu.evensson.primenumbers;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import eu.evensson.primenumbers.counters.NaivePrimeCounter;
import eu.evensson.primenumbers.counters.OptimizedNaivePrimeCounter;
import eu.evensson.primenumbers.counters.PrimeCounter;
import eu.evensson.primenumbers.counters.RememberingPrimeCounter;
import eu.evensson.primenumbers.counters.SievePrimeCounter;
import eu.evensson.primenumbers.counters.primelists.BitSetPrimeList;
import eu.evensson.primenumbers.counters.primelists.LongArrayPrimeList;
import eu.evensson.primenumbers.counters.primelists.PrimeList;

public class ApplicationInjector {

	public static Application injectApplication(final ApplicationScope scope) {
		return new Application(injectPrimeCounterMap(), injectPrinter());
	}

	private static Map<String, PrimeCounter> injectPrimeCounterMap() {
		final Map<String, PrimeCounter> primeCounterMap = new HashMap<>();
		primeCounterMap.put("naive", injectNaivePrimeCounter());
		primeCounterMap.put("opt_naive", injectOptimizedNaivePrimeCounter());
		primeCounterMap.put("remembering", injectRememberingPrimeCounter());
		primeCounterMap.put("bitset_sieve", injectBitSetSievePrimeCounter());
		primeCounterMap.put("longarr_sieve", injectLongArraySievePrimeCounter());
		return primeCounterMap;
	}

	private static PrimeCounter injectNaivePrimeCounter() {
		return new NaivePrimeCounter();
	}

	private static PrimeCounter injectOptimizedNaivePrimeCounter() {
		return new OptimizedNaivePrimeCounter();
	}

	private static PrimeCounter injectRememberingPrimeCounter() {
		return new RememberingPrimeCounter();
	}

	private static PrimeCounter injectBitSetSievePrimeCounter() {
		return new SievePrimeCounter(injectBitSetPrimeListFactory());
	}

	private static Function<Long, PrimeList> injectBitSetPrimeListFactory() {
		return maxPrime -> new BitSetPrimeList(maxPrime);
	}

	private static PrimeCounter injectLongArraySievePrimeCounter() {
		return new SievePrimeCounter(injectLongArrayPrimeListFactory());
	}

	private static Function<Long, PrimeList> injectLongArrayPrimeListFactory() {
		return maxPrime -> new LongArrayPrimeList(maxPrime);
	}

	private static Printer injectPrinter() {
		return string -> System.out.println(string);
	}

}
