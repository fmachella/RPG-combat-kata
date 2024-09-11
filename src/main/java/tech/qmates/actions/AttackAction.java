package tech.qmates.actions;

import tech.qmates.Character;
import tech.qmates.Distance;

public class AttackAction {
    private final tech.qmates.Character attacker;
    private final tech.qmates.Character victim;

    public AttackAction(tech.qmates.Character attacker, Character victim) {
        this.attacker = attacker;
        this.victim = victim;
    }

    public void attack(Distance distance) {
        AttackOutcome attack = this.attacker.attack(distance);
        attack.apply(this.victim);
    }
}
