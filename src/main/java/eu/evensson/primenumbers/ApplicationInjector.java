package eu.evensson.primenumbers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

import eu.evensson.primenumbers.counters.ForkJoinNaivePrimeCounter;
import eu.evensson.primenumbers.counters.ForkJoinSievePrimeCounter;
import eu.evensson.primenumbers.counters.FutureNaivePrimeCounter;
import eu.evensson.primenumbers.counters.FutureSievePrimeCounter;
import eu.evensson.primenumbers.counters.NaivePrimeCounter;
import eu.evensson.primenumbers.counters.OptimizedNaivePrimeCounter;
import eu.evensson.primenumbers.counters.ParallelStreamNaivePrimeCounter;
import eu.evensson.primenumbers.counters.PrimeCounter;
import eu.evensson.primenumbers.counters.RememberingPrimeCounter;
import eu.evensson.primenumbers.counters.SievePrimeCounter;
import eu.evensson.primenumbers.counters.StreamNaivePrimeCounter;
import eu.evensson.primenumbers.counters.primelists.BitSetPrimeList;
import eu.evensson.primenumbers.counters.primelists.BoolArrayPrimeList;
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
		primeCounterMap.put("stream_naive", injectStreamNaivePrimeCounter());
		primeCounterMap.put("par_stream_naive_1", injectParallelStreamNaivePrimeCounter(1));
		primeCounterMap.put("par_stream_naive_2", injectParallelStreamNaivePrimeCounter(2));
		primeCounterMap.put("par_stream_naive_3", injectParallelStreamNaivePrimeCounter(3));
		primeCounterMap.put("par_stream_naive_4", injectParallelStreamNaivePrimeCounter(4));
		primeCounterMap.put("par_stream_naive_5", injectParallelStreamNaivePrimeCounter(5));
		primeCounterMap.put("par_stream_naive_6", injectParallelStreamNaivePrimeCounter(6));
		primeCounterMap.put("par_stream_naive_7", injectParallelStreamNaivePrimeCounter(7));
		primeCounterMap.put("par_stream_naive_8", injectParallelStreamNaivePrimeCounter(8));
		primeCounterMap.put("opt_naive", injectOptimizedNaivePrimeCounter());
		primeCounterMap.put("remembering", injectRememberingPrimeCounter());
		primeCounterMap.put("bitset_sieve", injectBitSetSievePrimeCounter());
		primeCounterMap.put("longarr_sieve", injectLongArraySievePrimeCounter());
		primeCounterMap.put("boolarr_sieve", injectBoolArraySievePrimeCounter());
		primeCounterMap.put("future_boolarr_sieve_1", injectBoolArrayFutureSievePrimeCounter(1));
		primeCounterMap.put("future_boolarr_sieve_2", injectBoolArrayFutureSievePrimeCounter(2));
		primeCounterMap.put("future_boolarr_sieve_3", injectBoolArrayFutureSievePrimeCounter(3));
		primeCounterMap.put("future_boolarr_sieve_4", injectBoolArrayFutureSievePrimeCounter(4));
		primeCounterMap.put("future_boolarr_sieve_5", injectBoolArrayFutureSievePrimeCounter(5));
		primeCounterMap.put("future_boolarr_sieve_6", injectBoolArrayFutureSievePrimeCounter(6));
		primeCounterMap.put("future_boolarr_sieve_7", injectBoolArrayFutureSievePrimeCounter(7));
		primeCounterMap.put("future_boolarr_sieve_8", injectBoolArrayFutureSievePrimeCounter(8));
		primeCounterMap.put("forkjoin_boolarr_sieve_1", injectBoolArrayForkJoinSievePrimeCounter(1));
		primeCounterMap.put("forkjoin_boolarr_sieve_2", injectBoolArrayForkJoinSievePrimeCounter(2));
		primeCounterMap.put("forkjoin_boolarr_sieve_3", injectBoolArrayForkJoinSievePrimeCounter(3));
		primeCounterMap.put("forkjoin_boolarr_sieve_4", injectBoolArrayForkJoinSievePrimeCounter(4));
		primeCounterMap.put("forkjoin_boolarr_sieve_5", injectBoolArrayForkJoinSievePrimeCounter(5));
		primeCounterMap.put("forkjoin_boolarr_sieve_6", injectBoolArrayForkJoinSievePrimeCounter(6));
		primeCounterMap.put("forkjoin_boolarr_sieve_7", injectBoolArrayForkJoinSievePrimeCounter(7));
		primeCounterMap.put("forkjoin_boolarr_sieve_8", injectBoolArrayForkJoinSievePrimeCounter(8));
		return primeCounterMap;
	}

	private static PrimeCounter injectNaivePrimeCounter() {
		return new NaivePrimeCounter();
	}

	private static PrimeCounter injectFutureNaivePrimeCounter(final int threads) {
		return new FutureNaivePrimeCounter(injectWorkStealingPool(threads));
	}

	private static ExecutorService injectWorkStealingPool(final int threads) {
		return Executors.newWorkStealingPool(threads);
	}

	private static PrimeCounter injectForkJoinNaivePrimeCounter(final int threads) {
		return new ForkJoinNaivePrimeCounter(injectForkJoinPool(threads));
	}

	private static ForkJoinPool injectForkJoinPool(final int threads) {
		return new ForkJoinPool(threads);
	}

	private static PrimeCounter injectStreamNaivePrimeCounter() {
		return new StreamNaivePrimeCounter();
	}

	private static PrimeCounter injectParallelStreamNaivePrimeCounter(final int threads) {
		return new ParallelStreamNaivePrimeCounter(injectForkJoinPool(threads));
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

	private static PrimeCounter injectBoolArraySievePrimeCounter() {
		return new SievePrimeCounter(injectBoolArrayPrimeListFactory());
	}

	private static Function<Long, PrimeList> injectBoolArrayPrimeListFactory() {
		return maxPrime -> new BoolArrayPrimeList(maxPrime);
	}

	private static PrimeCounter injectBoolArrayFutureSievePrimeCounter(final int threads) {
		return new FutureSievePrimeCounter(
				injectWorkStealingPool(threads),
				injectBoolArrayPrimeListFactory());
	}

	private static PrimeCounter injectBoolArrayForkJoinSievePrimeCounter(final int threads) {
		return new ForkJoinSievePrimeCounter(
				injectForkJoinPool(threads),
				injectBoolArrayPrimeListFactory());
	}

	private static Printer injectPrinter() {
		return string -> System.out.println(string);
	}

}
