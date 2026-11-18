package org.grow.smartleadqualification.messages.exception;

public class MessageNotFoundException extends RuntimeException {
    public MessageNotFoundException(Long id) {
        super("Message with " + id + " not found.");
    }
}
