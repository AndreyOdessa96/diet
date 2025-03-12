package org.ivanov.andrey.diet;

import java.math.BigDecimal;

public enum Activity {
    MINIMAL(BigDecimal.valueOf(1.2)),
    WEAK(BigDecimal.valueOf(1.375)),
    MEDIUM(BigDecimal.valueOf(1.55)),
    HIGH(BigDecimal.valueOf(1.725)),
    EXTREME(BigDecimal.valueOf(1.9));

    private final BigDecimal factor;

    Activity(BigDecimal factor) {
        this.factor = factor;
    }

    public BigDecimal getFactor() {
        return factor;
    }
}