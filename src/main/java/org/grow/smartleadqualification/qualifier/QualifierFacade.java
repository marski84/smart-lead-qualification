package org.grow.smartleadqualification.qualifier;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class QualifierFacade {
    private final QualifierWebClient qualifierWebClient;
    private final QualificatorWebClientPropertiesConfig config;

    public LeadQualificationResponse getQualificationResponse(LeadQualificationRequest leadQualificationRequest) {
        LeadQualificationResponse response = qualifierWebClient.getQualificatorResponse(leadQualificationRequest);
        log.warn(response.answerText());
        return response;
    }
}
