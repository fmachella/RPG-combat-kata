package tech.qmates;

public class Ranged implements Weapon {
    @Override
    public Outcome attack(Distance distance) {
        return Outcome.HIT;
    }
}
