package org.grow.smartleadqualification.messages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record InboundMessageDto(
        @NotBlank
        @NotEmpty
        String message
) {
}
