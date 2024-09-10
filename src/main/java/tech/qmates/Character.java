package tech.qmates;

public class Character {
    private Health health;

    public Character(Health health) {
        this.health = health;
    }

    public Health deal(Character defender, Damage damage) {
//        return defender.health.damage(damage);
        throw new RuntimeException("Not implemented yet");
    }
}
