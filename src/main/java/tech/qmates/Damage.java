package tech.qmates;

import java.math.BigDecimal;

public record Damage(int damages) {

    public Damage applyMultiplier(BigDecimal multiplier) {
        return new Damage(multiplier.multiply(BigDecimal.valueOf(damages)).intValue());
    }
}
