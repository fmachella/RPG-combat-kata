package tech.qmates;

import tech.qmates.actions.*;
import tech.qmates.exceptions.InvalidAction;
import tech.qmates.weapons.AttackSkill;
import tech.qmates.weapons.Melee;

import java.util.HashSet;

public class Character {

    private final Level level;
    private final AttackSkill attackSkill;
    private final HealAction healAction;
    private Health health;
    private final Membership factions;

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
        this.factions = new Membership(new HashSet<>());
        this.health = health;
        this.level = level;
        this.attackSkill = attackSkill;
        attackSkill.of(this);
        this.healAction = new AlliedRestricted(this,new SimpleHealAction());
    }

    public Character(Health health,
                     Level level,
                     AttackSkill attackSkill,
                     Membership membership,
                     HealAction healAction) {
        this.factions = membership;
        this.health = health;
        this.level = level;
        this.attackSkill = attackSkill;
        attackSkill.of(this);
        this.healAction = healAction;
    }

    public Character(Membership membership) {
        this(Health.FULL,new Level(1),new Melee(),membership, new SimpleHealAction());
    }

    public Character(HealAction healAction) {
        this(Health.FULL,new Level(1),new Melee(),new Membership(new HashSet<>()), healAction);
    }

    public Character(AttackSkill attackSkill) {
        this(Health.FULL,new Level(1), attackSkill);
    }

    public Character(Health health, Level level, AttackSkill skill, Membership membership) {
        this.factions = membership;
        this.health = health;
        this.level = level;
        this.attackSkill = skill;
        attackSkill.of(this);
        this.healAction = new AlliedRestricted(this,new SimpleHealAction());
    }

    public Health attack(Character target, Distance distance) {
        AttackAction simpleAttackAction = new FactionCheck(this,target,new SimpleAttackAction(this, target));
        return simpleAttackAction.attack(distance);
    }

    public AttackOutcome tryHit(Distance distance) {
        return attackSkill.tryHit(distance);
    }

    public Health take(Heal heal) {
        if (this.isDead())
            throw new InvalidAction("You can't heal exploded chickens");
        if (this.health.isFull()) {
            throw new InvalidAction("You can't heal more! It's full");
        }
        this.health = this.health.heal(heal);
        return this.health;
    }

    public boolean isDead() {
        return this.health.equals(Health.ZERO);
    }

    public Health take(Damage damage) {
        Health remaining = this.health.damage(damage);
        this.health = remaining;
        if (remaining.isAKillingBlow()) {
            this.health = Health.ZERO;
        }
        return this.health;
    }

    public Health hit(Character target, Damage damage) {
        throwExceptionIfSame(target);
        DamageMultiplier multiplier = this.level.disparityEffect(target.level());
        Damage realDamage = multiplier.calculateReal(damage);

        return target.take(realDamage);
    }

    private void throwExceptionIfSame(Character target) {
        if (this.equals(target)) {
            throw new InvalidAction("You can't suicide. Are you fag?");
        }
    }

    public Health heals(Character wounded, Heal heal) {
        return healAction.heal(wounded, heal);
    }

    public Level level() {
        return this.level;
    }


    public boolean isHeAllied(Character friendOrFoe) {
        return factions.stream().anyMatch(faction -> faction.isHeAMember(friendOrFoe));
    }

    public void quit(Faction faction) {
        this.factions.remove(faction);
        faction.strikeOff(this);
    }

    public void join(Faction faction) {
        faction.subscribe(this);
        this.factions.add(faction);
    }

}
