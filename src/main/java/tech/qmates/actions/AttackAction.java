package tech.qmates.actions;

import tech.qmates.BasicCharacter;
import tech.qmates.Distance;
import tech.qmates.Character;

public class AttackAction {
    private final BasicCharacter attacker;
    private final Character victim;

    public AttackAction(BasicCharacter attacker, Character victim) {
        this.attacker = attacker;
        this.victim = victim;
    }

    public void attack(Distance distance) {
        AttackOutcome attack = this.attacker.attack(distance);
        attack.apply(this.victim);
    }
}
