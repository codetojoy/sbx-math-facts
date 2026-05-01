package models;

public class MathFacts {
    public long n;
    public boolean prime;

    public MathFacts() {}

    public MathFacts(long n, boolean prime) {
        this.n = n;
        this.prime = prime;
    }

    public long getN() { return n; }
    public boolean isPrime() { return prime; }
}
