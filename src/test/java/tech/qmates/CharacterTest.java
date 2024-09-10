package tech.qmates;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CharacterTest {
    @Test
    void a_character_hit_another_one_but_still_alive() {
        Character attacker = new Character(new Health(100));
        Character defender = new Character(new Health(100));

        Health remainingHealth = attacker.deal(defender,new Damage(5));

        assertEquals(defender.isDead(),false);
        assertEquals(remainingHealth,new Health(95));
    }

    @Test
    void a_character_hit_another_one_twice_but_still_alive() {
        Character attacker = new Character(new Health(100));
        Character defender = new Character(new Health(100));

                                    attacker.deal(defender,new Damage(5));
        Health remainingHealth =    attacker.deal(defender,new Damage(45));

        assertEquals(defender.isDead(),false);
        assertEquals(remainingHealth,new Health(50));
    }

    @Test
    void a_character_kills_another() {
        Character attacker = new Character(new Health(100));
        Character defender = new Character(new Health(20));

        attacker.deal(defender,new Damage(50));

        assertEquals(defender.isDead(),true);
    }

}
