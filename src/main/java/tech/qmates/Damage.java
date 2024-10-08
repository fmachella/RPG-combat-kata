package tech.qmates;

import java.math.BigDecimal;

public record Damage(int damages) {

    public static final Damage NULL = new Damage(0);

    public Damage applyMultiplier(BigDecimal multiplier) {
        return new Damage(multiplier.multiply(BigDecimal.valueOf(damages)).intValue());
    }
}
