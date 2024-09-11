package tech.qmates;

public class Melee implements Weapon {

    public static final int MAX_RANGE = 2;
    private Character owner;

    @Override
    public AttackOutcome tryHit(Distance distance) {
        if (distance.isWithin(MAX_RANGE)){
            return new Hit(owner);
        }
        return new Miss(owner);
    }

    @Override
    public void of(Character owner) {
        this.owner=owner;
    }
}
