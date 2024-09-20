package tech.qmates.actions;

import org.junit.jupiter.api.Test;
import tech.qmates.Character;
import tech.qmates.Heal;
import tech.qmates.Health;
import tech.qmates.exceptions.InvalidAction;

import static org.junit.jupiter.api.Assertions.*;

class HealActionTest {

    @Test
    void foe_heals_denied() {
        Character healer = new Character();
        Character wounded = new Character();
        HealAction enemy = new AlliedRestricted(healer,new SimpleHealAction());

        InvalidAction exception = assertThrowsExactly(InvalidAction.class, () -> enemy.heal(wounded, Heal.ANY));
        assertEquals("You can't heal an enemy!", exception.getMessage());
    }

    @Test
    void friend_heals_allowed() {
        Character healer = createAlwaysAlliedCharacter();
        Character wounded = new Character(new Health(10));
        HealAction enemy = new AlliedRestricted(healer,new SimpleHealAction());

        assertEquals(new Health(50),healer.heals(wounded, new Heal(40)));
    }

    private Character createAlwaysAlliedCharacter() {
        return new Character() {
            public boolean isHeAllied(Character friendOrFoe) {
                return true;
            }
        };
    }
}