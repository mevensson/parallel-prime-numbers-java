package eu.evensson.primenumbers.counters.primelists;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class PrimeListTest {

	public static interface PrimeListFactory {
		PrimeList create(long maxPrime);
	}

	private static final long MIN_PRIME = 2;

	@DataPoints
	public static PrimeListFactory[] primeListFactories = new PrimeListFactory[] {
			maxPrime -> new BitSetPrimeList(maxPrime),
			maxPrime -> new LongArrayPrimeList(maxPrime),
			maxPrime -> new BoolArrayPrimeList(maxPrime)
	};

	@DataPoints
	public static long[] maxPrimeDataPoints = {
			MIN_PRIME, 10, 63, 64, 65, 1000
	};

	@Theory
	public void shouldHaveAllSetWhenConstructed(
			final PrimeListFactory factory,
			final long maxPrime) {
		assumeThat(maxPrime, is(greaterThanOrEqualTo(MIN_PRIME)));

		final PrimeList primeList = factory.create(maxPrime);

		for (long n = MIN_PRIME; n <= maxPrime; n++) {
			assertThat(primeList.check(n), is(true));
		}

		assertThat(primeList.countSet(), is(maxPrime - MIN_PRIME + 1));
	}


	@Theory
	public void shouldNotBeSetAfterCross(
			final PrimeListFactory factory,
			final long maxPrime,
			final long toCross) {
		assumeThat(maxPrime, is(greaterThanOrEqualTo(MIN_PRIME)));
		assumeThat(toCross, is(greaterThanOrEqualTo(MIN_PRIME)));
		assumeThat(toCross, is(lessThanOrEqualTo(maxPrime)));

		final PrimeList primeList = factory.create(maxPrime);

		primeList.cross(toCross);

		assertThat(primeList.check(toCross), is(false));
	}

}
