package tech.qmates;

public class Hit implements AttackOutcome {
    private final Character owner;

    public Hit(Character owner) {
        this.owner = owner;
    }

    @Override
    public void apply(Character target) {
        this.owner.hit(target,new Damage(2));
    }
}
