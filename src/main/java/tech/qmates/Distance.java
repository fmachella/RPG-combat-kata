package tech.qmates;

public record Distance(int val) {

    public boolean isWithin(int maxRange) {
        return val<=maxRange;
    }
}
