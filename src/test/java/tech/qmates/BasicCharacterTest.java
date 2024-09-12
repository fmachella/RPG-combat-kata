package tech.qmates;

import org.junit.jupiter.api.Test;
import tech.qmates.actions.AttackOutcome;
import tech.qmates.exceptions.InvalidAction;
import tech.qmates.weapons.AttackSkill;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class BasicCharacterTest {
    @Test
    void a_character_hit_another_one_but_still_alive() {
        BasicCharacter attacker = new Character(new Health(100));
        Character defender = new Character(new Health(100));

        Health remainingHealth = attacker.hit(defender,new Damage(5));

        assertFalse(defender.isDead());
        assertEquals(new Health(95),remainingHealth);
    }

    @Test
    void a_character_hit_another_one_twice_but_still_alive() {
        BasicCharacter attacker = new Character(new Health(100));
        Character defender = new Character(new Health(100));

                                    attacker.hit(defender,new Damage(5));
        Health remainingHealth =    attacker.hit(defender,new Damage(45));

        assertFalse(defender.isDead());
        assertEquals(new Health(50),remainingHealth);
    }

    @Test
    void a_character_kills_another() {
        BasicCharacter attacker = new Character(new Health(100));
        Character defender = new Character(new Health(20));

        attacker.hit(defender,new Damage(50));

        assertTrue(defender.isDead());
    }

    @Test
    void overpower_character_kills_weaker_one() {
        BasicCharacter attacker = new Character(new Health(100),new Level(30));
        Character victim = new Character(new Health(30),new Level(1));
        attacker.hit(victim,new Damage(15));
        assertTrue(victim.isDead());
    }
    @Test

    void weak_character_cannot_kill_a_bigger_one() {
        BasicCharacter attacker = new Character(new Health(100),new Level(1));
        Character rock = new Character(new Health(100),new Level(100));
        attacker.hit(rock,new Damage(100));
        assertFalse(rock.isDead());
    }

    @Test
    void healer() {
        BasicCharacter healer = new Character();
        BasicCharacter wounded = new Character(new Health(17));

        Health result = healer.heals(wounded,new Heal(33));

        assertEquals(new Health(50),result);
    }

    @Test
    void healer_heals_an_allied() {
        Membership membership = new Membership(new HashSet<>());
        BasicCharacter healer = new Character(membership);
        BasicCharacter wounded = new Character(new Health(17));

        Health result = healer.heals(wounded,new Heal(33));

        assertEquals(new Health(50),result);
    }

    @Test
    void dead_player_can_not_be_healed() {
        BasicCharacter healer = new Character();
        BasicCharacter dead = new Character(Health.ZERO);

        Exception exception = assertThrows(InvalidAction.class, () -> healer.heals(dead,new Heal(33)));

        assertEquals("You can't heal exploded chickens", exception.getMessage());
        assertTrue(dead.isDead());
    }

    @Test
    void cannot_heal_fulfilled_characters() {
        BasicCharacter healer = new Character();
        BasicCharacter fulfilled = new Character();

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
        AttackSkill attackSkill = new AttackSkill() {
            private BasicCharacter owner;

            public AttackOutcome tryHit(Distance distance) {
                mock.registerCall("tryHit");
                return null;
            }
            public void of(BasicCharacter character) {
                this.owner=character;
            }
        };
        BasicCharacter attacker = new Character(attackSkill);
        attacker.attack(new Distance(5));

        assertEquals(1,mock.calls());
        assertTrue(mock.called("tryHit"));
    }

}
