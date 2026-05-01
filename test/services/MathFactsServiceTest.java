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

    @Test
    public void negativesAreNotPalindromic() {
        assertFalse(service.isPalindromic(-1));
        assertFalse(service.isPalindromic(-121));
    }

    @Test
    public void singleDigitsArePalindromic() {
        assertTrue(service.isPalindromic(0));
        assertTrue(service.isPalindromic(7));
        assertTrue(service.isPalindromic(9));
    }

    @Test
    public void multiDigitPalindromes() {
        assertTrue(service.isPalindromic(11));
        assertTrue(service.isPalindromic(121));
        assertTrue(service.isPalindromic(1221));
        assertTrue(service.isPalindromic(12321));
    }

    @Test
    public void multiDigitNonPalindromes() {
        assertFalse(service.isPalindromic(12));
        assertFalse(service.isPalindromic(123));
        assertFalse(service.isPalindromic(1231));
    }

    @Test
    public void factsForBundlesPalindromic() {
        var facts = service.factsFor(121);
        assertEquals(121, facts.n);
        assertFalse(facts.prime);
        assertTrue(facts.palindromic);
    }
}
