package tech.qmates.actions;

import tech.qmates.Distance;
import tech.qmates.Health;

public interface AttackAction {
    Health attack(Distance distance);
}
