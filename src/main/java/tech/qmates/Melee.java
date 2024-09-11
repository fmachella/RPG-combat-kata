package tech.qmates;

public class Melee implements Weapon {
    @Override
    public Outcome attack(Distance distance) {
        if (distance.isMeleeRange()){
            return Outcome.HIT;
        }
        return Outcome.MISS;
    }
}
