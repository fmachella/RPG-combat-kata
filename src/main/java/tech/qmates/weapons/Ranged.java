package tech.qmates.weapons;

import tech.qmates.Character;
import tech.qmates.Damage;
import tech.qmates.Distance;
import tech.qmates.actions.AttackOutcome;
import tech.qmates.actions.Hit;
import tech.qmates.actions.Miss;

public class Ranged implements AttackSkill {

    public static final int MAX_RANGE = 20;
    private Character owner;

    @Override
    public AttackOutcome tryHit(Distance distance) {
        if (distance.isWithin(MAX_RANGE)){
            return new Hit(this.owner, new Damage(2));
        }
        return new Miss();
    }

    @Override
    public void of(Character owner) {
        this.owner=owner;
    }
}
