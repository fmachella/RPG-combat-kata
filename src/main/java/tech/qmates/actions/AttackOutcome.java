package tech.qmates.actions;

import tech.qmates.Character;
import tech.qmates.Health;

public interface AttackOutcome {
    Health damage(Character target);
}
