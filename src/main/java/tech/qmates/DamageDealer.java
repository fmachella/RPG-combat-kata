package tech.qmates;

//non sono convinto di questa classe, potrebbe essere tranquillamente una responsabilità di level.
//aspetto di vedere come evolve e in caso faccio inline
public class DamageDealer {

    public Damage calculateDamage(Level myself, Level target, Damage damage) {
        if (target.isOverpowerFor(myself)) {
            return damage.halvenIt();
        }
        if (target.isWeakerFor(myself)){
            return damage.doubleIt();
        }
        return damage;
    }

}
