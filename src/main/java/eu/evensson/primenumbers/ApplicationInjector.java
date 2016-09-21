package eu.evensson.primenumbers;

public class ApplicationInjector {

	public static Application injectApplication(final ApplicationScope scope) {
		return new Application(injectPrimeCounter(), injectPrinter());
	}

	private static PrimeCounter injectPrimeCounter() {
		return new NaivePrimeCounter();
	}

	private static Printer injectPrinter() {
		return string -> System.out.println(string);
	}

}
