package org.grow.smartleadqualification.qualifier;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "qualificator.api")
@Getter
@Setter
class QualificatorWebClientPropertiesConfig {
    String url;
    String model;
    String token;
}
