package tech.qmates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FightAcceptanceTest {
    @Test
    void a_character_hit_another_one_but_still_alive() {
        Character attacker = new Character(new Health(100));
        Character defender = new Character(new Health(100));
        Health remainingHealth = attacker.deal(defender,new Damage(5));

        assertEquals(remainingHealth,new Health(95));
    }

}
