package tech.qmates.actions;

import tech.qmates.Character;
import tech.qmates.Damage;
import tech.qmates.Health;

public class Miss implements AttackOutcome {


    @Override
    public Health damage(Character target) {
        return target.take(Damage.NULL);
    }
}
