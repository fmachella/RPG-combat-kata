package tech.qmates.actions;

import tech.qmates.Character;

public class Miss implements AttackOutcome {
    private final tech.qmates.Character owner;

    public Miss(tech.qmates.Character owner) {
        this.owner = owner;
    }

    @Override
    public void apply(Character target) {

    }
}
