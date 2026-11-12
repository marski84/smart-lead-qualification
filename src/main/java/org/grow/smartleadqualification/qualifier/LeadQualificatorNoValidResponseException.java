package org.grow.smartleadqualification.qualifier;

public class LeadQualificatorNoValidResponseException extends RuntimeException {
    public LeadQualificatorNoValidResponseException() {
        super("No output text in response");
    }
}
