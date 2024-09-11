package tech.qmates;

public class AttackAction {
    private final Character attacker;
    private final Character victim;

    public AttackAction(Character attacker, Character victim) {
        this.attacker = attacker;
        this.victim = victim;
    }

    public void attack(Distance distance) {
        AttackOutcome attack = this.attacker.attack(distance);
        attack.apply(this.victim);
    }
}
