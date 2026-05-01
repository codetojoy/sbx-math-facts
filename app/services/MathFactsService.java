package services;

import models.MathFacts;

public class MathFactsService {

    public MathFacts factsFor(long n) {
        return new MathFacts(n, isPrime(n));
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
}
