package tech.qmates;

import org.junit.jupiter.api.Test;
import tech.qmates.actions.AttackAction;
import tech.qmates.weapons.Melee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AttackActionTest {

    @Test
    void attacker_hit_and_makes_damage() {
        Mock victimMock = new Mock();
        Character meleeAttacker = new Character(new Melee());
        Character victim = new SpiedCharacter(victimMock);

        AttackAction attackAction = new AttackAction(meleeAttacker,victim);

        attackAction.attack(new Distance(1));

        assertEquals(1,victimMock.calls());
        assertTrue(victimMock.called("take(Damage)"));
    }

    @Test
    void attacker_miss_and_no_damage_taken() {
        Mock victimMock = new Mock();
        Character meleeAttacker = new Character(new Melee());
        Character victim = new SpiedCharacter(victimMock);

        AttackAction attackAction = new AttackAction(meleeAttacker,victim);

        attackAction.attack(new Distance(7));

        assertEquals(0,victimMock.calls());
    }

    private static class SpiedCharacter extends Character {
        private final Mock victimMock;

        public SpiedCharacter(Mock victimMock) {
            this.victimMock = victimMock;
        }

        @Override
        public Health take(Damage damage) {
            victimMock.registerCall("take(Damage)");
            return super.take(damage);
        }
    }
}
