package tech.qmates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {
    @Test
    void a_character_hit_another_one_but_still_alive() {
        Character attacker = new Character(new Health(100));
        Character defender = new Character(new Health(100));

        Health remainingHealth = attacker.hit(defender,new Damage(5));

        assertFalse(defender.isDead());
        assertEquals(new Health(95),remainingHealth);
    }

    @Test
    void a_character_hit_another_one_twice_but_still_alive() {
        Character attacker = new Character(new Health(100));
        Character defender = new Character(new Health(100));

                                    attacker.hit(defender,new Damage(5));
        Health remainingHealth =    attacker.hit(defender,new Damage(45));

        assertFalse(defender.isDead());
        assertEquals(new Health(50),remainingHealth);
    }

    @Test
    void a_character_kills_another() {
        Character attacker = new Character(new Health(100));
        Character defender = new Character(new Health(20));

        attacker.hit(defender,new Damage(50));

        assertTrue(defender.isDead());
    }

    @Test
    void healer() {
        Character healer = new Character();
        Character wounded = new Character(new Health(17));

        Health result = healer.heals(wounded,new Heal(33));

        assertEquals(new Health(50),result);
    }
}
