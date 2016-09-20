package eu.evensson.primenumbers;

public class Application {

	private final PrimeCounter primeFinder;

	public Application(final PrimeCounter primeFinder) {
		this.primeFinder = primeFinder;
	}

	public void run(final String[] args) {
		primeFinder.countPrimes(Long.parseLong(args[0]));
	}

}
