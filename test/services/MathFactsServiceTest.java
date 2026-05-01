package services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MathFactsServiceTest {

    private final MathFactsService service = new MathFactsService();

    @Test
    public void negativesAreNotPrime() {
        assertFalse(service.isPrime(-1));
        assertFalse(service.isPrime(-7));
    }

    @Test
    public void zeroAndOneAreNotPrime() {
        assertFalse(service.isPrime(0));
        assertFalse(service.isPrime(1));
    }

    @Test
    public void smallPrimes() {
        assertTrue(service.isPrime(2));
        assertTrue(service.isPrime(3));
        assertTrue(service.isPrime(5));
        assertTrue(service.isPrime(7));
        assertTrue(service.isPrime(13));
    }

    @Test
    public void smallComposites() {
        assertFalse(service.isPrime(4));
        assertFalse(service.isPrime(9));
        assertFalse(service.isPrime(15));
        assertFalse(service.isPrime(25));
    }

    @Test
    public void largerKnownPrime() {
        assertTrue(service.isPrime(7919));
        assertFalse(service.isPrime(7919L * 7L));
    }

    @Test
    public void factsForBundlesNAndPrime() {
        var facts = service.factsFor(17);
        assertEquals(17, facts.n);
        assertTrue(facts.prime);
    }
}
