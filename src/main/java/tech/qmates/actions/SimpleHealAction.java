package tech.qmates.actions;

import tech.qmates.Character;
import tech.qmates.Heal;
import tech.qmates.Health;

public class SimpleHealAction implements HealAction {

    @Override
    public Health heal(Character wounded, Heal heal) {
        return wounded.take(heal);
    }
}
