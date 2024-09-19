package tech.qmates;

import tech.qmates.actions.AttackOutcome;
import tech.qmates.exceptions.InvalidAction;
import tech.qmates.weapons.AttackSkill;
import tech.qmates.weapons.Melee;

import java.util.HashSet;

public class Character {

    private final Level level;
    private final AttackSkill attackSkill;
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
        this(health, level, attackSkill, new Membership(new HashSet<>()));
    }

    public Character(Health health, Level level, AttackSkill attackSkill, Membership membership) {
        this.factions = membership;
        this.health = health;
        this.level = level;
        this.attackSkill = attackSkill;
        attackSkill.of(this);
    }

    public Character(Membership membership) {
        this(Health.FULL,new Level(1),new Melee(),membership);
    }

    public Character(AttackSkill attackSkill) {
        this(Health.FULL,new Level(1), attackSkill);
    }

    public AttackOutcome attack(Distance distance) {
        return this.tryHit(distance);
    }

    public AttackOutcome tryHit(Distance distance) {
        return attackSkill.tryHit(distance);
    }

    public Health take(Heal heal) {
        if (this.isDead())
            throw new InvalidAction("You can't heal exploded chickens");
        if (this.health.equals(Health.FULL)) {
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

    public Health heals(Character wounded, Heal heal) {
        return wounded.take(heal);
    }

    public Level level() {
        return this.level;
    }


    private void throwExceptionIfSame(Character target) {
        if (this.equals(target)) {
            throw new InvalidAction("You can't suicide. Are you fag?");
        }
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
