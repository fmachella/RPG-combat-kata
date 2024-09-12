package tech.qmates.weapons;

import tech.qmates.BasicCharacter;
import tech.qmates.Distance;
import tech.qmates.actions.AttackOutcome;
import tech.qmates.actions.Hit;
import tech.qmates.actions.Miss;

public class Ranged implements AttackSkill {

    public static final int MAX_RANGE = 20;
    private BasicCharacter owner;

    @Override
    public AttackOutcome tryHit(Distance distance) {
        if (distance.isWithin(MAX_RANGE)){
            return new Hit(this.owner);
        }
        return new Miss();
    }

    @Override
    public void of(BasicCharacter owner) {
        this.owner=owner;
    }
}
