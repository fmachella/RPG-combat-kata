package tech.qmates;

import tech.qmates.actions.AttackOutcome;
import tech.qmates.exceptions.InvalidAction;
import tech.qmates.weapons.AttackSkill;
import tech.qmates.weapons.Melee;

import java.util.HashSet;

public class Character implements BasicCharacter, RecruitableCharacter {
    private final ConcreteRecruitableCharacter concreteRecruitableCharacter;
    private final ConcreteBasicCharacter concreteBasicCharacter;

    public Character(Health health) {
        this(health,new Level(1));
    }

    public Character() {
        this(Health.FULL);
    }

    public Character(Health health, Level level) {
        this(health,level,new Melee());
    }

    public Character(Health health, Level level, AttackSkill attackSkill) {
        this(health, level, attackSkill, new Membership(new HashSet<>()));
    }

    public Character(Health health, Level level, AttackSkill attackSkill, Membership membership) {
        this.concreteRecruitableCharacter = new ConcreteRecruitableCharacter(membership,this);
        concreteBasicCharacter = new ConcreteBasicCharacter(health, level, attackSkill);
    }

    public Character(Membership membership) {
        this(Health.FULL,new Level(1),new Melee(),membership);
    }

    public Character(AttackSkill attackSkill) {
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
        if (this.equals(target)) {
            throw new InvalidAction("You can't suicide. Are you fag?");
        }
        return concreteBasicCharacter.hit(target,damage);
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
