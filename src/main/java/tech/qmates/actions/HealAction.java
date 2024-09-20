package tech.qmates.actions;

import tech.qmates.Character;
import tech.qmates.Heal;
import tech.qmates.Health;

public interface HealAction {
    Health heal(Character wounded, Heal heal);
}
