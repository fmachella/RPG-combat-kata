package tech.qmates;

import tech.qmates.actions.AttackOutcome;
import tech.qmates.exceptions.InvalidAction;
import tech.qmates.weapons.AttackSkill;
import tech.qmates.weapons.Melee;

import java.util.HashSet;

public class ComposedCharacter implements Character {
    private final RecruitableCharacter concreteRecruitableCharacter;
    private final BasicCharacter concreteBasicCharacter;

    public ComposedCharacter(Health health) {
        this(health,new Level(1));
    }

    public ComposedCharacter() {
        this(Health.FULL);
    }

    public ComposedCharacter(Health health, Level level) {
        this(health,level,new Melee());
    }

    public ComposedCharacter(Health health, Level level, AttackSkill attackSkill) {
        this(health, level, attackSkill, new Membership(new HashSet<>()));
    }

    public ComposedCharacter(Health health, Level level, AttackSkill attackSkill, Membership membership) {
        this.concreteRecruitableCharacter = new ConcreteRecruitableCharacter(membership,this);
        this.concreteBasicCharacter = new ConcreteBasicCharacter(health, level, attackSkill);
    }

    public ComposedCharacter(Membership membership) {
        this(Health.FULL,new Level(1),new Melee(),membership);
    }

    public ComposedCharacter(AttackSkill attackSkill) {
        this(Health.FULL,new Level(1), attackSkill);
    }

    @Override
    public Health heals(BasicCharacter wounded, Heal heal) {
        return concreteBasicCharacter.heals(wounded,heal);
    }

    @Override
    public AttackOutcome attack(Distance distance) {
        return concreteBasicCharacter.attack(distance);
    }

    @Override
    public Health hit(BasicCharacter target, Damage damage) {
        throwExceptionIfSame(target);
        return concreteBasicCharacter.hit(target,damage);
    }

    private void throwExceptionIfSame(BasicCharacter target) {
        if (this.equals(target)) {
            throw new InvalidAction("You can't suicide. Are you fag?");
        }
    }

    @Override
    public boolean isDead() {
        return concreteBasicCharacter.isDead();
    }

    @Override
    public Health take(Damage damage) {
        return concreteBasicCharacter.take(damage);
    }

    @Override
    public Health take(Heal heal) {
        return concreteBasicCharacter.take(heal);
    }

    @Override
    public void join(Faction faction) {
        concreteRecruitableCharacter.join(faction);
    }

    @Override
    public void quit(Faction faction) {
        concreteRecruitableCharacter.quit(faction);
    }

    @Override
    public boolean isHeAllied(RecruitableCharacter character) {
        return concreteRecruitableCharacter.isHeAllied(character);
    }

    @Override
    public Level level() {
        return concreteBasicCharacter.level();
    }

}
