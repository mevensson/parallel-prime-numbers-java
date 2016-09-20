package eu.evensson.primenumbers;

public class Application {
	private static final String NUM_PRIMES_FORMAT =
			"Number of primes less than %d are %d.";

	private static final String USAGE =
			"Usage: prime-numbers <max_prime>";

	private final PrimeCounter primeFinder;
	private final Printer printer;

	public Application(final PrimeCounter primeFinder, final Printer printer) {
		this.primeFinder = primeFinder;
		this.printer = printer;
	}

	public void run(final String[] args) {
		if (args.length != 1) {
			printer.print(USAGE);
			return;
		}
		final long maxPrime = Long.parseLong(args[0]);
		final long numPrimes = primeFinder.countPrimes(maxPrime);
		printer.print(String.format(NUM_PRIMES_FORMAT, maxPrime, numPrimes));
	}

}
