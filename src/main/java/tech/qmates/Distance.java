package tech.qmates;

record Distance(int val) {
    public boolean isMeleeRange() {
        return val<=2;
    }
}
