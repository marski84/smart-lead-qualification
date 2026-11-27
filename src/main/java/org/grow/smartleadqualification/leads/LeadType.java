package org.grow.smartleadqualification.leads;

import java.util.Arrays;

enum LeadType {
    DEMO_REQUEST, PRICING_INQUIRY, PARTNERSHIP, SUPPORT, OTHER;

    static LeadType getLeadType(String leadType) {
        if (leadType == null) {
            throw new IllegalArgumentException("Lead type cannot be null");
        }
        String normalized = leadType.trim();
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(normalized))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown lead type: '" + leadType + "'"));
    }
}
