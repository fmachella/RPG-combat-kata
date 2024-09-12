package tech.qmates;

import tech.qmates.actions.AttackOutcome;
import tech.qmates.exceptions.InvalidAction;
import tech.qmates.weapons.AttackSkill;
import tech.qmates.weapons.Melee;

import java.util.HashSet;

public class Character implements BasicCharacter,FactionableCharacter {
    private final Level level;
    private final AttackSkill attackSkill;
    private Health health;
    private Membership factions;

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
        this.level = level;
        this.attackSkill = attackSkill;
        this.health = health;
        this.factions = membership;
        attackSkill.of(this);
    }

    public Character(Membership membership) {
        this();
        this.factions = membership;
    }

    public Character(AttackSkill attackSkill) {
        this(Health.FULL,new Level(1), attackSkill);
    }

    @Override
    public Health heals(BasicCharacter wounded, Heal heal) {
        return wounded.take(heal);
    }

    @Override
    public Health hit(BasicCharacter target, Damage damage) {
        if (this.equals(target)) {
            throw new InvalidAction("You can't suicide. Are you fag?");
        }
        DamageMultiplier multiplier = this.level.disparityEffect(target.level());
        Damage realDamage = multiplier.calculateReal(damage);

        return target.take(realDamage);
    }

    @Override
    public boolean isDead() {
        return this.health.equals(Health.ZERO);
    }

    @Override
    public Health take(Damage damage) {
        Health remaining = this.health.damage(damage);
        this.health=remaining;
        if (remaining.isAKillingBlow()){
            this.health=Health.ZERO;
        }
        return this.health;
    }

    @Override
    public Health take(Heal heal) {
        if (this.isDead())
            throw new InvalidAction("You can't heal exploded chickens");
        if (this.health.equals(Health.FULL)){
            throw new InvalidAction("You can't heal more! It's full");
        }
        this.health= this.health.heal(heal);
        return this.health;
    }

    @Override
    public AttackOutcome attack(Distance distance) {
        return this.attackSkill.tryHit(distance);
    }

    @Override
    public void join(Faction faction) {
        faction.subscribe(this);
        this.factions.add(faction);
    }

    @Override
    public void quit(Faction faction) {
        this.factions.remove(faction);
        faction.strikeOff(this);
    }

    @Override
    public boolean isHeAllied(FactionableCharacter character) {
        return factions.stream().anyMatch(faction -> faction.isHeAMember(character));
    }

    @Override
    public Level level() {
        return this.level;
    }
}
