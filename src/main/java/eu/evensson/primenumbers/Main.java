package eu.evensson.primenumbers;

public class Main {
	public static void main(final String[] args) {
		final ApplicationScope scope = new ApplicationScope(args);
		final Application application = ApplicationInjector.injectApplication(scope);
		application.run();
	}
}
