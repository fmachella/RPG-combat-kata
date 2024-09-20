package tech.qmates.actions;

import tech.qmates.Character;
import tech.qmates.Distance;
import tech.qmates.Health;
import tech.qmates.exceptions.InvalidAction;

public class FactionCheck implements AttackAction {
    private final Character attacker;
    private final Character target;
    private final SimpleAttackAction delegate;

    public FactionCheck(Character attacker,
                        Character target,
                        SimpleAttackAction delegate) {
        this.attacker = attacker;
        this.target = target;
        this.delegate = delegate;
    }

    @Override
    public Health attack(Distance distance) {
        if (attacker.isHeAllied(target))
            throw new InvalidAction("You can't attack an ally! Are you idiot!?");
        return delegate.attack(distance);
    }
}
