package eu.evensson.primenumbers;

import java.util.HashMap;
import java.util.Map;

public class ApplicationInjector {

	public static Application injectApplication(final ApplicationScope scope) {
		return new Application(injectPrimeCounterMap(), injectPrinter());
	}

	private static Map<String, PrimeCounter> injectPrimeCounterMap() {
		final Map<String, PrimeCounter> primeCounterMap = new HashMap<>();
		primeCounterMap.put("naive", injectNaivePrimeCounter());
		primeCounterMap.put("opt_naive", injectOptimizedNaivePrimeCounter());
		return primeCounterMap;
	}

	private static PrimeCounter injectNaivePrimeCounter() {
		return new NaivePrimeCounter();
	}

	private static PrimeCounter injectOptimizedNaivePrimeCounter() {
		return new OptimizedNaivePrimeCounter();
	}

	private static Printer injectPrinter() {
		return string -> System.out.println(string);
	}

}
