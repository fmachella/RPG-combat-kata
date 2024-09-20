package tech.qmates.actions;

import tech.qmates.Character;
import tech.qmates.Heal;
import tech.qmates.Health;
import tech.qmates.exceptions.InvalidAction;

public class AlliedRestricted implements HealAction {
    private final Character healer;
    private final HealAction delegate;

    public AlliedRestricted(Character healer, HealAction delegate) {
        this.healer = healer;
        this.delegate = delegate;
    }

    @Override
    public Health heal(Character wounded, Heal heal) {
        if (!healer.isHeAllied(wounded))
            throw new InvalidAction("You can't heal an enemy!");
        return delegate.heal(wounded, heal);
    }
}
