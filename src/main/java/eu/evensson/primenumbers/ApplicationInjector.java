package eu.evensson.primenumbers;

public class ApplicationInjector {

	public static Application injectApplication(final ApplicationScope scope) {
		return new Application();
	}

}
