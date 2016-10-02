package eu.evensson.primenumbers;

import java.util.HashMap;
import java.util.Map;

import eu.evensson.primenumbers.counters.BitSetSievePrimeCounter;
import eu.evensson.primenumbers.counters.LongArraySievePrimeCounter;
import eu.evensson.primenumbers.counters.NaivePrimeCounter;
import eu.evensson.primenumbers.counters.OptimizedNaivePrimeCounter;
import eu.evensson.primenumbers.counters.PrimeCounter;
import eu.evensson.primenumbers.counters.RememberingPrimeCounter;

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
		return new BitSetSievePrimeCounter();
	}

	private static PrimeCounter injectLongArraySievePrimeCounter() {
		return new LongArraySievePrimeCounter();
	}

	private static Printer injectPrinter() {
		return string -> System.out.println(string);
	}

}
