package tech.qmates.actions;

import tech.qmates.Character;
import tech.qmates.Distance;
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
    public void attack(Distance distance) {
        if (attacker.isHeAllied(target))
            throw new InvalidAction("You can't attack an ally! Are you idiot!?");
        delegate.attack(distance);
    }
}
