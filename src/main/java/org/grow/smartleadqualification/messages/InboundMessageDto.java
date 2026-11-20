package org.grow.smartleadqualification.messages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record InboundMessageDto(
        @NotBlank(message = "Message cannot be blank")
        @NotEmpty(message = "Message cannot be empty")
        String message
) {
}
