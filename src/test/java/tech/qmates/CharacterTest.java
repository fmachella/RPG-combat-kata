package tech.qmates;

import org.junit.jupiter.api.Test;
import tech.qmates.exceptions.InvalidAction;

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

    @Test
    void dead_player_can_not_be_healed() {
        Character healer = new Character();
        Character dead = new Character(Health.ZERO);

        Exception exception = assertThrows(InvalidAction.class, () -> healer.heals(dead,new Heal(33)));

        assertEquals("You can't heal exploded chickens", exception.getMessage());
        assertTrue(dead.isDead());
    }

    @Test
    void cannot_heal_fulfilled_characters() {
        Character healer = new Character();
        Character fulfilled = new Character();

        Exception exception = assertThrows(InvalidAction.class, () -> healer.heals(fulfilled,new Heal(33)));

        assertEquals("You can't heal more! It's full", exception.getMessage());
    }

    @Test
    void cannot_suicide() {
        Character suicidal = new Character();
        Exception exception = assertThrows(InvalidAction.class,() -> suicidal.hit(suicidal,new Damage(2)));
        assertEquals("You can't suicide. Are you fag?", exception.getMessage());
    }

    @Test
    void character_delegate_to_weapon_the_attack() {
        final Mock mock = new Mock();
        Weapon weapon = new Weapon() {
            private Character owner;

            public AttackOutcome tryHit(Distance distance) {
                mock.registerCall("tryHit");
                return null;
            }
            public void of(Character character) {
                this.owner=character;
            }
        };
        Character attacker = new Character(weapon);
        attacker.attack(new Distance(5));

        assertEquals(1,mock.calls());
        assertTrue(mock.called("tryHit"));
    }

}
