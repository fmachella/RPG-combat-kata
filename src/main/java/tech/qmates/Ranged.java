package tech.qmates;

public class Ranged implements Weapon {

    public static final int MAX_RANGE = 20;
    private Character owner;

    @Override
    public AttackOutcome tryHit(Distance distance) {
        if (distance.isWithin(MAX_RANGE)){
            return new Hit(this.owner);
        }
        return new Miss(this.owner);
    }

    @Override
    public void of(Character owner) {
        this.owner=owner;
    }
}
