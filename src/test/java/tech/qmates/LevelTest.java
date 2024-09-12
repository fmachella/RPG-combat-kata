package tech.qmates;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LevelTest {

    @Test
    void normal_damage_same_levels() {
        Level myself = new Level(1);
        Level target = new Level(1);
        DamageMultiplier actual = target.disparityEffect(myself);
        assertEquals(BigDecimal.valueOf(1),actual.multiplier());
    }

    @Test
    void overpower_halve_damage() {
        Level myself = new Level(1);
        Level target = new Level(6);
        DamageMultiplier multiplier = myself.disparityEffect(target);
        assertEquals(DamageMultiplier.HALF,multiplier);
    }

    @Test
    void overpower_halve_damage_but_nullify_on_weak_hit() {
        Level myself = new Level(1);
        Level target = new Level(6);
        DamageMultiplier multiplier = myself.disparityEffect(target);
        assertEquals(DamageMultiplier.HALF,multiplier);
    }

    @Test
    void weaker_double_damage() {
        Level myself = new Level(6);
        Level target = new Level(1);
        DamageMultiplier multiplier = myself.disparityEffect(target);
        assertEquals(DamageMultiplier.DOUBLE,multiplier);
    }
}