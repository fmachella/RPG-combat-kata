package tech.qmates;

import org.junit.jupiter.api.Test;
import tech.qmates.actions.AttackOutcome;
import tech.qmates.actions.Hit;
import tech.qmates.actions.Miss;
import tech.qmates.weapons.AttackSkill;
import tech.qmates.weapons.Melee;
import tech.qmates.weapons.Ranged;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class WeaponsTest {
    @Test
    void melee_attack_miss() {
        AttackSkill attackSkill = new Melee();
        AttackOutcome attackOutcome = attackSkill.tryHit(new Distance(5));
        assertInstanceOf(Miss.class, attackOutcome);
    }

    @Test
    void melee_attack_hit() {
        AttackSkill attackSkill = new Melee();
        AttackOutcome attackOutcome = attackSkill.tryHit(new Distance(1));
        assertInstanceOf(Hit.class, attackOutcome);
    }

    @Test
    void ranged_attack_hit() {
        AttackSkill attackSkill = new Ranged();
        AttackOutcome attackOutcome = attackSkill.tryHit(new Distance(10));
        assertInstanceOf(Hit.class, attackOutcome);
    }

    @Test
    void ranged_attack_miss() {
        AttackSkill attackSkill = new Ranged();
        AttackOutcome attackOutcome = attackSkill.tryHit(new Distance(21));
        assertInstanceOf(Miss.class, attackOutcome);
    }

}
