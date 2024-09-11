package tech.qmates;

record Distance(int val) {

    public boolean isWithin(int maxRange) {
        return val<=maxRange;
    }
}
