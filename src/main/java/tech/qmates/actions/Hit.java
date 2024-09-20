package tech.qmates.actions;

import tech.qmates.Character;
import tech.qmates.Damage;
import tech.qmates.Health;

public class Hit implements AttackOutcome {
    private final Character owner;
    private final Damage damage;

    public Hit(Character owner, Damage damage) {
        this.owner = owner;
        this.damage = damage;
    }

    @Override
    public Health damage(Character target) {
        return this.owner.hit(target, damage);
    }
}
