package tech.qmates;

import org.junit.jupiter.api.Test;
import tech.qmates.actions.AttackAction;
import tech.qmates.actions.FactionCheck;
import tech.qmates.actions.SimpleAttackAction;
import tech.qmates.exceptions.InvalidAction;
import tech.qmates.weapons.Melee;

import static org.junit.jupiter.api.Assertions.*;

public class AttackActionTest {

    @Test
    void attacker_hit_and_makes_damage() {
        Spy victimSpy = new Spy();
        Character meleeAttacker = new Character(new Melee());
        Character victim = new SpiedCharacter(victimSpy);

        AttackAction attackAction = new SimpleAttackAction(meleeAttacker,victim);

        attackAction.attack(new Distance(1));

        assertEquals(1, victimSpy.calls());
        assertTrue(victimSpy.called("take(Damage)"));
    }

    @Test
    void attacker_miss_and_no_damage_taken() {
        Spy victimSpy = new Spy();
        Character meleeAttacker = new Character(new Melee());
        Character victim = new SpiedCharacter(victimSpy);

        AttackAction attackAction = new SimpleAttackAction(meleeAttacker,victim);

        attackAction.attack(new Distance(7));

        assertEquals(0, victimSpy.calls());
    }

    @Test
    void allies_cannot_damages() {
        Character alliedAttacker = new Character(){
            @Override
            public boolean isHeAllied(Character friendOrFoe) {
                return true;
            }
        };
        Character anyAlly = new Character();
        AttackAction attackAction = new FactionCheck(alliedAttacker,
                anyAlly,
                new SimpleAttackAction(alliedAttacker,anyAlly));

        Exception exception = assertThrows(InvalidAction.class,() -> attackAction.attack(new Distance(1)));
        assertEquals("You can't attack an ally! Are you idiot!?", exception.getMessage());
    }

    private static class SpiedCharacter extends Character {
        private final Spy victimSpy;

        public SpiedCharacter(Spy victimSpy) {
            this.victimSpy = victimSpy;
        }

        @Override
        public Health take(Damage damage) {
            victimSpy.registerCall("take(Damage)");
            return super.take(damage);
        }
    }
}
