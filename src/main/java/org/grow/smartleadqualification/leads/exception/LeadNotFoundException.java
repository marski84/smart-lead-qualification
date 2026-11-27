package org.grow.smartleadqualification.leads.exception;

public class LeadNotFoundException extends RuntimeException {
    public LeadNotFoundException(Long id) {
        super("Lead with id: " + id + " not found");
    }
}
