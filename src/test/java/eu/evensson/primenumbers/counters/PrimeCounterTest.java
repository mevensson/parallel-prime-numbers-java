package eu.evensson.primenumbers.counters;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import eu.evensson.primenumbers.counters.primelists.BitSetPrimeList;
import eu.evensson.primenumbers.counters.primelists.LongArrayPrimeList;

@RunWith(Parameterized.class)
public class PrimeCounterTest {

	@Parameters
	public static Iterable<? extends Object> primeCounters() {
		return asList(
				new SievePrimeCounter(maxPrime -> new BitSetPrimeList(maxPrime)),
				new SievePrimeCounter(maxPrime -> new LongArrayPrimeList(maxPrime)),
				new NaivePrimeCounter(),
				new OptimizedNaivePrimeCounter(),
				new RememberingPrimeCounter());
	}

	@Parameter
	public PrimeCounter primeCounter;

	@Test
	public void shouldCountPrimes() {
		assertThat(primeCounter.countPrimes(0L), is(0L));
		assertThat(primeCounter.countPrimes(1L), is(0L));
		assertThat(primeCounter.countPrimes(2L), is(1L));
		assertThat(primeCounter.countPrimes(3L), is(2L));
		assertThat(primeCounter.countPrimes(4L), is(2L));
		assertThat(primeCounter.countPrimes(5L), is(3L));
		assertThat(primeCounter.countPrimes(6L), is(3L));
		assertThat(primeCounter.countPrimes(7L), is(4L));
		assertThat(primeCounter.countPrimes(8L), is(4L));
		assertThat(primeCounter.countPrimes(9L), is(4L));
		assertThat(primeCounter.countPrimes(10L), is(4L));
		assertThat(primeCounter.countPrimes(11L), is(5L));
		assertThat(primeCounter.countPrimes(100L), is(25L));
		assertThat(primeCounter.countPrimes(1000L), is(168L));
		assertThat(primeCounter.countPrimes(10000L), is(1229L));
	}

}
