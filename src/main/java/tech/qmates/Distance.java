package tech.qmates;

record Distance(int val) {
    public boolean isMeleeRange() {
        return val<=2;
    }

    public boolean isRangedRange() {
        return val<=20;
    }
}
