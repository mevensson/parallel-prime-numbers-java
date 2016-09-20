package eu.evensson.primenumbers;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

public class ApplicationTest {
	private static final long MAX_PRIME = 42;
	private static final long NUM_PRIMES = 13;

	private static final String USAGE_STRING = "Usage: prime-numbers <max_prime>";

	private final PrimeCounter primeCounter = mock(PrimeCounter.class);
	private final Printer printer = mock(Printer.class);
	private final Application application = new Application(primeCounter, printer);

	@Test
	public void shouldCallFindPrimeWithFirstArgument() throws Exception {
		application.run(new String[] { Long.toString(MAX_PRIME) });

		verify(primeCounter).countPrimes(MAX_PRIME);
	}

	@Test
	public void shouldPrintNumberOfPrimes() throws Exception {
		when(primeCounter.countPrimes(anyLong())).thenReturn(NUM_PRIMES);

		application.run(new String[] { Long.toString(MAX_PRIME) });

		verify(printer).print("Number of primes less than " + MAX_PRIME
				+ " are " + NUM_PRIMES + ".");
	}

	@Test
	public void shouldPrintUsageWhenTooFewArguments() throws Exception {
		application.run(new String[] {});

		verify(printer).print(USAGE_STRING);
	}

	@Test
	public void shouldPrintUsageWhenTooManyArguments() throws Exception {
		application.run(new String[] { "1", "2"});

		verify(printer).print(USAGE_STRING);
	}
}
