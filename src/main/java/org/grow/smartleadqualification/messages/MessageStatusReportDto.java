package org.grow.smartleadqualification.messages;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link Message}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record MessageStatusReportDto(Long id, @NotBlank String text, Instant receivedAt, MessageStatus status,
                                     Instant processedDate) implements Serializable {
}