package tech.qmates;

import tech.qmates.actions.AttackOutcome;
import tech.qmates.exceptions.InvalidAction;
import tech.qmates.weapons.AttackSkill;

class ConcreteBasicCharacter implements BasicCharacter {
    private final Level level;
    private final AttackSkill attackSkill;
    private Health health;

    public ConcreteBasicCharacter(Health health, Level level, AttackSkill attackSkill1) {
        this.level = level;
        this.attackSkill = attackSkill1;
        this.health = health;
        attackSkill.of(this);
    }

    @Override
    public AttackOutcome attack(Distance distance) {
        return this.tryHit(distance);
    }

    public AttackOutcome tryHit(Distance distance) {
        return attackSkill.tryHit(distance);
    }

    public Health take(Heal heal) {
        if (this.isDead())
            throw new InvalidAction("You can't heal exploded chickens");
        if (this.health.equals(Health.FULL)) {
            throw new InvalidAction("You can't heal more! It's full");
        }
        this.health = this.health.heal(heal);
        return this.health;
    }

    public boolean isDead() {
        return this.health.equals(Health.ZERO);
    }

    public Health take(Damage damage) {
        Health remaining = this.health.damage(damage);
        this.health = remaining;
        if (remaining.isAKillingBlow()) {
            this.health = Health.ZERO;
        }
        return this.health;
    }

    public Health hit(BasicCharacter target, Damage damage) {
        DamageMultiplier multiplier = this.level.disparityEffect(target.level());
        Damage realDamage = multiplier.calculateReal(damage);

        return target.take(realDamage);
    }

    public Health heals(BasicCharacter wounded, Heal heal) {
        return wounded.take(heal);
    }

    public Level level() {
        return this.level;
    }
}
