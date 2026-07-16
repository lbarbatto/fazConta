package com.pororoca.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for {@link DisplayFormatter}.
 *
 * <p>Validates that integer-like values are shown without
 * decimal places while fractional values keep their precision.</p>
 */
class DisplayFormatterTest {

    @Test
    void integerValueShouldDropDecimalPart() {
        assertEquals(
                "10",
                DisplayFormatter.format(10.0)
        );
    }

    @Test
    void fractionalValueShouldKeepDecimals() {
        assertEquals(
                "10.5",
                DisplayFormatter.format(10.5)
        );
    }

    @Test
    void negativeIntegerValueShouldDropDecimalPart() {
        assertEquals(
                "-3",
                DisplayFormatter.format(-3.0)
        );
    }
}
