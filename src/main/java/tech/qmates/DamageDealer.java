package tech.qmates;

public class DamageDealer {

    public Damage calculateDamage(Level myself, Level target, Damage damage) {
        Damage realDamage = damage;
        if (target.isOverpowerFor(myself)) {
            realDamage = damage.halven();
        }
        return realDamage;
    }

}
