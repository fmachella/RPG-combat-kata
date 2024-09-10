package tech.qmates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LifeTest {

    @Test
    void life_damage_decrease() {
        Life life = new Life(20);
        Life remaining=life.damage(10);
        assertEquals(remaining,new Life(10));
    }

    @Test
    void life_heal_increase() {
        Life life = new Life(20);
        Life remaining=life.heal(10);
        assertEquals(remaining,new Life(30));
    }
}