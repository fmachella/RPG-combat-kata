package tech.qmates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class WeaponsTest {
    @Test
    void melee_attack_miss() {
        Weapon weapon = new Melee();
        AttackOutcome attackOutcome = weapon.tryHit(new Distance(5));
        assertInstanceOf(Miss.class, attackOutcome);
    }

    @Test
    void melee_attack_hit() {
        Weapon weapon = new Melee();
        AttackOutcome attackOutcome = weapon.tryHit(new Distance(1));
        assertInstanceOf(Hit.class, attackOutcome);
    }

    @Test
    void ranged_attack_hit() {
        Weapon weapon = new Ranged();
        AttackOutcome attackOutcome = weapon.tryHit(new Distance(10));
        assertInstanceOf(Hit.class, attackOutcome);
    }

    @Test
    void ranged_attack_miss() {
        Weapon weapon = new Ranged();
        AttackOutcome attackOutcome = weapon.tryHit(new Distance(21));
        assertInstanceOf(Miss.class, attackOutcome);
    }

}
