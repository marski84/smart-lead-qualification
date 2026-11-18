package org.grow.smartleadqualification.messages;

import java.time.Instant;

public record OutboundMessageDto(
        Long id,
        String message,
        Instant receivedDate
) {
}
