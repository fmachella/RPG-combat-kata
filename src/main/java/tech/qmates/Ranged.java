package tech.qmates;

public class Ranged implements Weapon {
    @Override
    public Outcome attack(Distance distance) {
        if (distance.isRangedRange()){
            return Outcome.HIT;
        }
        return Outcome.MISS;
    }
}
