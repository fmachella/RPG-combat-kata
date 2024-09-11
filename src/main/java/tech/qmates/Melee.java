package tech.qmates;

public class Melee implements Weapon {

    public static final int MAX_RANGE = 2;

    @Override
    public AttackOutcome tryHit(Distance distance) {
        if (distance.isWithin(MAX_RANGE)){
            return new Hit();
        }
        return new Miss();
    }
}
