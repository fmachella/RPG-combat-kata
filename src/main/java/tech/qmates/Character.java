package tech.qmates;

public class Character {
    private final Level level;
    private Health health;

    public Character(Health health) {
        this.level=new Level(1);
        this.health = health;
    }

    public Character() {
        this(new Health(1000));
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

    private record Level(int level) {
    }
}
