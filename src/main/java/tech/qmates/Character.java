package tech.qmates;

public class Character {
    private Health health;

    public Character(Health health) {
        this.health = health;
    }

    public Health deal(Character defender, Damage damage) {
        Health left = defender.take(damage);
        return left;
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

    public boolean isDead() {
        return this.health.equals(Health.ZERO);
    }
}
