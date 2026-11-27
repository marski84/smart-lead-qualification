package org.grow.smartleadqualification.leads;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotEmpty;

import java.io.Serializable;

/**
 * DTO for {@link Lead}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record LeadReportDto(Long id, @NotEmpty String title, LeadType type, Urgency urgency,
                            @NotEmpty String summary) implements Serializable {
}