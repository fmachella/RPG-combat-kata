package tech.qmates.weapons;

import tech.qmates.BasicCharacter;
import tech.qmates.Distance;
import tech.qmates.actions.AttackOutcome;

public interface AttackSkill {
    AttackOutcome tryHit(Distance distance);

    void of(BasicCharacter owner);
}
