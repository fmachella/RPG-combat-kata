package tech.qmates;

import java.math.BigDecimal;

public record DamageMultiplier(BigDecimal multiplier) {
    public static final DamageMultiplier SAME = new DamageMultiplier(BigDecimal.ONE);
    public static final DamageMultiplier HALF = new DamageMultiplier(BigDecimal.valueOf(0.5));
    public static final DamageMultiplier DOUBLE = new DamageMultiplier(BigDecimal.valueOf(2));

    public Damage apply(Damage damage) {
        return damage.applyMultiplier(multiplier);
    }
}
