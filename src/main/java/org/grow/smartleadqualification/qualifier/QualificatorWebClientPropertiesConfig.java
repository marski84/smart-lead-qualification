package org.grow.smartleadqualification.qualifier;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "qualificator.api")
@Getter
@Setter
class QualificatorWebClientPropertiesConfig {
    @NotBlank
    @NotEmpty
    String url;

    @NotBlank
    @NotEmpty
    String model;

    @NotBlank
    @NotEmpty
    String token;
}
