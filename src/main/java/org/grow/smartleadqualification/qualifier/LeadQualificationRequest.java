package org.grow.smartleadqualification.qualifier;

public record LeadQualificationRequest(
        String model,
        String instructions,
        String input,
        double temperature,
        Reasoning reasoning
) {

    public static LeadQualificationRequest of(String model, String inboundMessage) {
        return new LeadQualificationRequest(
                model,
                Instructions.modelInstructions,
                inboundMessage,
                0,
                new Reasoning("low")
        );
    }

    private record Reasoning(String effort) {
    }
}