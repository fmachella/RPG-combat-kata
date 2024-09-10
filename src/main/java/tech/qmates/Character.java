package tech.qmates;

import tech.qmates.exceptions.InvalidAction;

public class Character {
    private final Level level;
    private Health health;

    public Character(Health health) {
        this.level=new Level(1);
        this.health = health;
    }

    public Character() {
        this(Health.FULL);
    }

    public Health heals(Character wounded, Heal heal) {
        return wounded.take(heal);
    }

    public Health hit(Character defender, Damage damage) {
        if (this.equals(defender)) {
            throw new InvalidAction();
        }
        return defender.take(damage);
    }

    public boolean isDead() {
        return this.health.equals(Health.ZERO);
    }

    private Health take(Damage damage) {
        Health remaining = this.health.damage(damage);
        if (remaining.isAKillingBlow()){
            this.health=Health.ZERO;
            return this.health;
        }
        this.health=remaining;
        return remaining;
    }

    private Health take(Heal heal) {
        if (this.isDead())
            return Health.ZERO;
        if (this.health.equals(Health.FULL)){
            return this.health;
        }
        this.health= this.health.heal(heal);
        return this.health;
    }

}
record Level(int level) {
}
