package tech.qmates.actions;

import tech.qmates.Character;
import tech.qmates.Distance;

public class SimpleAttackAction implements AttackAction {
    private final Character attacker;
    private final Character victim;

    public SimpleAttackAction(Character attacker, Character victim) {
        this.attacker = attacker;
        this.victim = victim;
    }

    @Override
    public void attack(Distance distance) {
        AttackOutcome attack = this.attacker.tryHit(distance);
        attack.apply(this.victim);
    }
}
