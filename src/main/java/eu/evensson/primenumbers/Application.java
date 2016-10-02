package eu.evensson.primenumbers;

import java.util.Map;

import eu.evensson.primenumbers.counters.PrimeCounter;

public class Application {
	private static final String NUM_PRIMES_FORMAT =
			"Number of primes less than or equal to %d are %d.";

	private static final String USAGE =
			"Usage: prime-numbers <max_prime>";

	private final Map<String, PrimeCounter> primeCounterMap;
	private final Printer printer;

	public Application(final Map<String, PrimeCounter> primeCounterMap, final Printer printer) {
		this.primeCounterMap = primeCounterMap;
		this.printer = printer;
	}

	public void run(final String[] args) {
		if (!isCorrectNumberOfArguments(args)) {
			printUsage();
			return;
		}
		final long maxPrime = parseMaxPrime(args);
		final String algorithm = args[1];
		final PrimeCounter primeCounter = primeCounterMap.get(algorithm);
		final long numPrimes = primeCounter.countPrimes(maxPrime);
		printNumberOfPrimes(maxPrime, numPrimes);
	}

	private boolean isCorrectNumberOfArguments(final String[] args) {
		return args.length == 2;
	}

	private void printUsage() {
		printer.print(USAGE);
	}

	private long parseMaxPrime(final String[] args) {
		return Long.parseLong(args[0]);
	}

	private void printNumberOfPrimes(final long maxPrime,
			final long numPrimes) {
		printer.print(String.format(NUM_PRIMES_FORMAT, maxPrime, numPrimes));
	}

}
