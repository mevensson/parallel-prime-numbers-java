package eu.evensson.primenumbers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

public class ApplicationTest {
	private static final long MAX_PRIME = 42;

	private final PrimeCounter primeCounter = mock(PrimeCounter.class);
	private final Application application = new Application(primeCounter);

	@Test
	public void shouldCallFindPrimeWithFirstArgument() throws Exception {
		application.run(new String[] { Long.toString(MAX_PRIME) });

		verify(primeCounter).countPrimes(MAX_PRIME);
	}
}
