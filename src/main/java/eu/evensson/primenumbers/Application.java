package eu.evensson.primenumbers;

public class Application {
	private static final String NUM_PRIMES_FORMAT =
			"Number of primes less than or equal to %d are %d.";

	private static final String USAGE =
			"Usage: prime-numbers <max_prime>";

	private final PrimeCounter primeFinder;
	private final Printer printer;

	public Application(final PrimeCounter primeFinder, final Printer printer) {
		this.primeFinder = primeFinder;
		this.printer = printer;
	}

	public void run(final String[] args) {
		if (!isCorrectNumberOfArguments(args)) {
			printUsage();
			return;
		}
		final long maxPrime = parseMaxPrime(args);
		final long numPrimes = primeFinder.countPrimes(maxPrime);
		printNumberOfPrimes(maxPrime, numPrimes);
	}

	private boolean isCorrectNumberOfArguments(final String[] args) {
		return args.length == 1;
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
