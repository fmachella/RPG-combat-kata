package tech.qmates;

import tech.qmates.actions.AttackOutcome;
import tech.qmates.exceptions.InvalidAction;
import tech.qmates.weapons.AttackSkill;
import tech.qmates.weapons.Melee;

import java.util.HashSet;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;

public class Character {
    private final DamageDealer damageDealer;
    private final Level level;
    private final AttackSkill attackSkill;
    private Health health;
    private HashSet<Faction> factions;

    public Character(Health health) {
        this(health,new Level(1));
    }

    public Character() {
        this(Health.FULL);
    }

    public Character(Health health, Level level) {
        this(health,level,new DamageDealer());
    }

    public Character(Health health, Level level, DamageDealer damageDealer) {
        this(health,level,damageDealer,new Melee());
    }

    public Character(Health health, Level level, DamageDealer damageDealer, AttackSkill attackSkill) {
        this(health, level, damageDealer,attackSkill,new HashSet<>());
    }

    public Character(Health health, Level level, DamageDealer damageDealer, AttackSkill attackSkill, HashSet<Faction> factionsMemory) {
        this.damageDealer = damageDealer;
        this.level = level;
        this.attackSkill = attackSkill;
        this.health = health;
        this.factions = factionsMemory;
        attackSkill.of(this);
    }

    public Character(HashSet<Faction> factions) {
        this();
        this.factions = factions;
    }

    public Character(AttackSkill attackSkill) {
        this(Health.FULL,new Level(1),new DamageDealer(), attackSkill);
    }

    public Health heals(Character wounded, Heal heal) {
        return wounded.take(heal);
    }

    public Health hit(Character target, Damage damage) {
        if (this.equals(target)) {
            throw new InvalidAction("You can't suicide. Are you fag?");
        }
        Damage realDamage = damageDealer.calculateDamage(this.level,target.level, damage);
        return target.take(realDamage);
    }

    public boolean isDead() {
        return this.health.equals(Health.ZERO);
    }

    protected Health take(Damage damage) {
        Health remaining = this.health.damage(damage);
        this.health=remaining;
        if (remaining.isAKillingBlow()){
            this.health=Health.ZERO;
        }
        return this.health;
    }

    protected Health take(Heal heal) {
        if (this.isDead())
            throw new InvalidAction("You can't heal exploded chickens");
        if (this.health.equals(Health.FULL)){
            throw new InvalidAction("You can't heal more! It's full");
        }
        this.health= this.health.heal(heal);
        return this.health;
    }

    public AttackOutcome attack(Distance distance) {
        return this.attackSkill.tryHit(distance);
    }

    public void join(Faction faction) {
        faction.subscribe(this);
        this.factions.add(faction);
    }

    public void quit(Faction faction) {
        this.factions.remove(faction);
        faction.strikeOff(this);
    }

    public boolean isHeAllied(Character character) {
        return factions.stream().anyMatch(faction -> faction.isHeAMember(character));
    }
}
