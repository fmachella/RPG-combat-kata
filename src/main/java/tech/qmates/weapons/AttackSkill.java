package tech.qmates.weapons;

import tech.qmates.Character;
import tech.qmates.Distance;
import tech.qmates.actions.AttackOutcome;

public interface AttackSkill {
    AttackOutcome tryHit(Distance distance);

    void of(Character owner);
}
