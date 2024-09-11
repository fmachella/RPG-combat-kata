package tech.qmates;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WeaponsTest {
    @Test
    void melee_attack_miss() {
        Weapon weapon = new Melee();
        Outcome outcome = weapon.attack(new Distance(5));
        assertEquals(Outcome.MISS,outcome);
    }

    @Test
    void melee_attack_hit() {
        Weapon weapon = new Melee();
        Outcome outcome = weapon.attack(new Distance(1));
        assertEquals(Outcome.HIT,outcome);
    }

    @Test
    void ranged_attack_hit() {
        Weapon weapon = new Ranged();
        Outcome outcome = weapon.attack(new Distance(10));
        assertEquals(Outcome.HIT,outcome);
    }

    @Test
    void ranged_attack_miss() {
        Weapon weapon = new Ranged();
        Outcome outcome = weapon.attack(new Distance(21));
        assertEquals(Outcome.MISS,outcome);
    }

}
