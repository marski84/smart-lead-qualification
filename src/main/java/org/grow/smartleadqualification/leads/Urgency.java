package org.grow.smartleadqualification.leads;

import java.util.Arrays;

enum Urgency {
    LOW, MEDIUM, HIGH;

    public static Urgency getUrgency(String urgency) {
        if (urgency == null) {
            throw new IllegalArgumentException("Urgency cannot be null");
        }
        String normalized = urgency.trim();
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(normalized))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown urgency: '" + urgency + "'"));
    }
}
