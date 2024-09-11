package tech.qmates;

import tech.qmates.exceptions.InvalidAction;

public class Character {
    private final DamageDealer damageDealer;
    private final Level level;
    private final Weapon weapon;
    private Health health;

    public Character(Health health) {
        this(health,new Level(1));
    }

    public Character() {
        this(Health.FULL);
    }

    public Character(Health health, Level level) {
        this(health,level,new DamageDealer());
    }

    public Character(Health health, Level level, DamageDealer damageDealer) {
        this(health,level,damageDealer,new Melee());
    }

    public Character(Health health, Level level, DamageDealer damageDealer, Weapon weapon) {
        this.level=level;
        this.health = health;
        this.damageDealer = damageDealer;
        this.weapon = weapon;
    }

    public Character(Weapon weapon) {
        this(Health.FULL,new Level(1),new DamageDealer(),weapon);
    }

    public Health heals(Character wounded, Heal heal) {
        return wounded.take(heal);
    }

    public Health hit(Character target, Damage damage) {
        if (this.equals(target)) {
            throw new InvalidAction("You can't suicide. Are you fag?");
        }
        Damage realDamage = damageDealer.calculateDamage(this.level,target.level, damage);
        return target.take(realDamage);
    }

    public boolean isDead() {
        return this.health.equals(Health.ZERO);
    }

    protected Health take(Damage damage) {
        Health remaining = this.health.damage(damage);
        this.health=remaining;
        if (remaining.isAKillingBlow()){
            this.health=Health.ZERO;
        }
        return this.health;
    }

    protected Health take(Heal heal) {
        if (this.isDead())
            throw new InvalidAction("You can't heal exploded chickens");
        if (this.health.equals(Health.FULL)){
            throw new InvalidAction("You can't heal more! It's full");
        }
        this.health= this.health.heal(heal);
        return this.health;
    }

    public AttackOutcome attack(Distance distance) {
        return this.weapon.tryHit(distance);
    }
}
