package tech.qmates;

public class Miss implements AttackOutcome {
    private final Character owner;

    public Miss(Character owner) {
        this.owner = owner;
    }

    @Override
    public void apply(Character target) {

    }
}
