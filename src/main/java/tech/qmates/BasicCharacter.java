package tech.qmates;

import tech.qmates.actions.AttackOutcome;

public interface BasicCharacter {
    Health heals(BasicCharacter wounded, Heal heal);

    Health hit(BasicCharacter target, Damage damage);

    boolean isDead();

    Health take(Damage damage);

    Health take(Heal heal);

    AttackOutcome attack(Distance distance);

    Level level();
}
