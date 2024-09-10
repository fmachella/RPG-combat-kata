package tech.qmates;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FightAcceptanceTest {
    @Test
    @Disabled
    void a_character_hit_another_one_but_still_alive() {
        Character attacker = new Character(new Life(100));
        Character defender = new Character(new Life(100));
        Life remainingLife = attacker.hit(new Damage(5));

        assertEquals(remainingLife,new Life(95));
    }

}
