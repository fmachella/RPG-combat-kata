package tech.qmates.actions;

import tech.qmates.BasicCharacter;
import tech.qmates.Damage;
import tech.qmates.Character;

public class Hit implements AttackOutcome {
    private final BasicCharacter owner;

    public Hit(BasicCharacter owner) {
        this.owner = owner;
    }

    @Override
    public void apply(Character target) {
        this.owner.hit(target,new Damage(2));
    }
}
