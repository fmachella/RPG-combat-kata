package tech.qmates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthTest {

    @Test
    void life_damage_decrease() {
        Health health = new Health(20);
        Health remaining= health.damage(10);
        assertEquals(remaining,new Health(10));
    }

    @Test
    void life_heal_increase() {
        Health health = new Health(20);
        Health remaining= health.heal(10);
        assertEquals(remaining,new Health(30));
    }
}