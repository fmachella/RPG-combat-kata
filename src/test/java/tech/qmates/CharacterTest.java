package tech.qmates;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import tech.qmates.actions.AlliedRestricted;
import tech.qmates.actions.AttackOutcome;
import tech.qmates.actions.HealAction;
import tech.qmates.actions.SimpleHealAction;
import tech.qmates.exceptions.InvalidAction;
import tech.qmates.weapons.AttackSkill;
import tech.qmates.weapons.Melee;

import java.util.HashSet;
import java.util.Set;

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
    void overpower_character_kills_weaker_one() {
        Character attacker = new Character(new Health(100),new Level(30));
        Character victim = new Character(new Health(30),new Level(1));
        attacker.hit(victim,new Damage(15));
        assertTrue(victim.isDead());
    }
    @Test
    void weak_character_cannot_kill_a_bigger_one() {
        Character attacker = new Character(new Health(100),new Level(1));
        Character rock = new Character(new Health(100),new Level(100));
        Health remaining = attacker.hit(rock, new Damage(100));

        assertFalse(rock.isDead());
        assertEquals(new Health(50),remaining);
    }

    @Test
    void healer_heal_a_wounded_one() {
        Character healer = new Character(new SimpleHealAction());
        Character wounded = new Character(new Health(17));

        Health result = healer.heals(wounded,new Heal(33));

        assertEquals(new Health(50),result);
    }

    @Test
    void healer_heals_an_ally() {
        HashSet<Character> factionMembers = new HashSet<>();
        Faction axis = new Faction(factionMembers);
        Membership membership = new Membership(new HashSet<>(Set.of(axis)));
        Character healer = new Character(Health.FULL,new Level(1),new Melee(),membership);
        Character wounded = new Character(new Health(17),new Level(1),new Melee(),membership);
        factionMembers.add(healer);factionMembers.add(wounded);

        Health result = healer.heals(wounded,new Heal(33));

        assertEquals(new Health(50),result);
    }

    @Test
    void dead_player_can_not_be_healed() {
        Character healer = new Character(new SimpleHealAction());
        Character dead = new Character(Health.ZERO);

        Exception exception = assertThrows(InvalidAction.class, () -> healer.heals(dead,new Heal(33)));

        assertEquals("You can't heal exploded chickens", exception.getMessage());
        assertTrue(dead.isDead());
    }

    @Test
    void cannot_heal_fulfilled_characters() {
        Character healer = new Character(new SimpleHealAction());
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
        final Spy spy = new Spy();
        AttackSkill attackSkill = new AttackSkill() {
            private Character owner;

            public AttackOutcome tryHit(Distance distance) {
                spy.registerCall("tryHit");
                return null;
            }
            public void of(Character character) {
                this.owner=character;
            }
        };
        Character attacker = new Character(attackSkill);
        attacker.tryHit(new Distance(5));

        assertEquals(1, spy.calls());
        assertTrue(spy.called("tryHit"));
    }

}
