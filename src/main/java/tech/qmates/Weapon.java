package tech.qmates;

public interface Weapon {
    AttackOutcome tryHit(Distance distance);

    void of(Character owner);
}
