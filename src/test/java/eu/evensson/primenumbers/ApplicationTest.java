package eu.evensson.primenumbers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ApplicationTest {
	private static final long MAX_PRIME = 42;
	private static final long NUM_PRIMES = 13;

	private static final String USAGE_STRING = "Usage: prime-numbers <max_prime>";

	private final PrimeCounter firstPrimeCounter = mock(PrimeCounter.class);
	private final PrimeCounter secondPrimeCounter = mock(PrimeCounter.class);
	private final Map<String, PrimeCounter> primeCounterMap = new HashMap<String, PrimeCounter>() {{
		put("first", firstPrimeCounter);
		put("second", secondPrimeCounter);
	}};
	private final Printer printer = mock(Printer.class);
	private final Application application = new Application(primeCounterMap, printer);

	@Test
	public void shouldCallFindPrimeWithFirstArgOnAlgorithmMatchingSecondArg() throws Exception {
		application.run(new String[] { Long.toString(MAX_PRIME), "first" });

		verify(firstPrimeCounter).countPrimes(MAX_PRIME);
	}

	@Test
	public void shouldCallFindPrimeWithFirstArgOnAlgorithmMatchingSecondArg2() throws Exception {
		application.run(new String[] { Long.toString(MAX_PRIME), "second" });

		verify(secondPrimeCounter).countPrimes(MAX_PRIME);
	}

	@Test
	public void shouldPrintNumberOfPrimes() throws Exception {
		when(firstPrimeCounter.countPrimes(anyLong())).thenReturn(NUM_PRIMES);

		application.run(new String[] { Long.toString(MAX_PRIME), "first" });

		verify(printer).print("Number of primes less than or equal to " + MAX_PRIME
				+ " are " + NUM_PRIMES + ".");
	}

	@Test
	public void shouldPrintUsageWhenTooFewArguments() throws Exception {
		application.run(new String[] { "1"});

		verify(printer).print(USAGE_STRING);
	}

	@Test
	public void shouldPrintUsageWhenTooManyArguments() throws Exception {
		application.run(new String[] { "1", "2", "3"});

		verify(printer).print(USAGE_STRING);
	}
}
