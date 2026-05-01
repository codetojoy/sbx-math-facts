package models;

public class MathFacts {
    public long n;
    public boolean prime;
    public boolean palindromic;

    public MathFacts() {}

    public MathFacts(long n, boolean prime, boolean palindromic) {
        this.n = n;
        this.prime = prime;
        this.palindromic = palindromic;
    }

    public long getN() { return n; }
    public boolean isPrime() { return prime; }
    public boolean isPalindromic() { return palindromic; }
}
