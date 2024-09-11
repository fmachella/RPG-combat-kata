package tech.qmates;

public class Ranged implements Weapon {

    public static final int MAX_RANGE = 20;

    @Override
    public AttackOutcome tryHit(Distance distance) {
        if (distance.isWithin(MAX_RANGE)){
            return new Hit();
        }
        return new Miss();
    }
}
