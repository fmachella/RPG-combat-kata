package tech.qmates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LifeTest {

    @Test
    void life_decrease() {
        Life life = new Life(20);
        Life remaining=life.decrease(10);
        assertEquals(remaining,new Life(10));
    }
}