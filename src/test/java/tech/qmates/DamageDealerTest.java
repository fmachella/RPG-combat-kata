package tech.qmates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DamageDealerTest {

    DamageDealer damageDealer = new DamageDealer();

    @Test
    void normal_damage_same_levels() {
        Damage actual = damageDealer.calculateDamage(new Level(1), new Level(1), new Damage(10));
        assertEquals(new Damage(10),actual);
    }

    @Test
    void overpower_halve_damage() {
        Damage actual = damageDealer.calculateDamage(new Level(1), new Level(6), new Damage(10));
        assertEquals(new Damage(5),actual);
    }

    @Test
    void overpower_halve_damage_but_nullify_on_weak_hit() {
        Damage actual = damageDealer.calculateDamage(new Level(1), new Level(6), new Damage(1));
        assertEquals(Damage.ZERO,actual);
    }
}