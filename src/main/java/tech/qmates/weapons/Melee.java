package tech.qmates.weapons;

import tech.qmates.Character;
import tech.qmates.Damage;
import tech.qmates.Distance;
import tech.qmates.actions.AttackOutcome;
import tech.qmates.actions.Hit;
import tech.qmates.actions.Miss;

public class Melee implements AttackSkill {

    public static final int MAX_RANGE = 2;
    private final Damage damage;
    private Character owner;

    public Melee() {
        damage = new Damage(2);
    }

    @Override
    public AttackOutcome tryHit(Distance distance) {
        if (distance.isWithin(MAX_RANGE)){
            return new Hit(this.owner, damage);
        }
        return new Miss();
    }

    @Override
    public void of(Character owner) {
        this.owner=owner;
    }
}
