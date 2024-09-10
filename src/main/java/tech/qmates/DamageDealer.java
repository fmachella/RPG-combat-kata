package tech.qmates;

class DamageDealer {

    public Damage calculateDamage(Level myself, Level defender, Damage damage) {
        Damage realDamage = damage;
        if (defender.diff(myself) >= 5) {
            realDamage = damage.halven();
        }
        return realDamage;
    }

}
