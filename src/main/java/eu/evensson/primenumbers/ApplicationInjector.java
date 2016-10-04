package eu.evensson.primenumbers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

import eu.evensson.primenumbers.counters.ForkJoinNaivePrimeCounter;
import eu.evensson.primenumbers.counters.FutureNaivePrimeCounter;
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
		primeCounterMap.put("future_naive_1", injectFutureNaivePrimeCounter(1));
		primeCounterMap.put("future_naive_2", injectFutureNaivePrimeCounter(2));
		primeCounterMap.put("future_naive_3", injectFutureNaivePrimeCounter(3));
		primeCounterMap.put("future_naive_4", injectFutureNaivePrimeCounter(4));
		primeCounterMap.put("future_naive_5", injectFutureNaivePrimeCounter(5));
		primeCounterMap.put("future_naive_6", injectFutureNaivePrimeCounter(6));
		primeCounterMap.put("future_naive_7", injectFutureNaivePrimeCounter(7));
		primeCounterMap.put("future_naive_8", injectFutureNaivePrimeCounter(8));
		primeCounterMap.put("forkjoin_naive_1", injectForkJoinNaivePrimeCounter(1));
		primeCounterMap.put("forkjoin_naive_2", injectForkJoinNaivePrimeCounter(2));
		primeCounterMap.put("forkjoin_naive_3", injectForkJoinNaivePrimeCounter(3));
		primeCounterMap.put("forkjoin_naive_4", injectForkJoinNaivePrimeCounter(4));
		primeCounterMap.put("forkjoin_naive_5", injectForkJoinNaivePrimeCounter(5));
		primeCounterMap.put("forkjoin_naive_6", injectForkJoinNaivePrimeCounter(6));
		primeCounterMap.put("forkjoin_naive_7", injectForkJoinNaivePrimeCounter(7));
		primeCounterMap.put("forkjoin_naive_8", injectForkJoinNaivePrimeCounter(8));
		primeCounterMap.put("opt_naive", injectOptimizedNaivePrimeCounter());
		primeCounterMap.put("remembering", injectRememberingPrimeCounter());
		primeCounterMap.put("bitset_sieve", injectBitSetSievePrimeCounter());
		primeCounterMap.put("longarr_sieve", injectLongArraySievePrimeCounter());
		return primeCounterMap;
	}

	private static PrimeCounter injectNaivePrimeCounter() {
		return new NaivePrimeCounter();
	}

	private static PrimeCounter injectFutureNaivePrimeCounter(final int threads) {
		return new FutureNaivePrimeCounter(Executors.newWorkStealingPool(threads));
	}

	private static PrimeCounter injectForkJoinNaivePrimeCounter(final int threads) {
		return new ForkJoinNaivePrimeCounter(injectForkJoinPool(threads));
	}

	private static ForkJoinPool injectForkJoinPool(final int threads) {
		return new ForkJoinPool(threads);
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
