package services;

import models.MathFacts;

public class MathFactsService {

    public MathFacts factsFor(long n) {
        return new MathFacts(n, isPrime(n), isPalindromic(n));
    }

    public boolean isPrime(long n) {
        if (n < 2) return false;
        if (n < 4) return true;
        if (n % 2 == 0) return false;
        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public boolean isPalindromic(long n) {
        if (n < 0) return false;
        String s = Long.toString(n);
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
