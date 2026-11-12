package org.grow.smartleadqualification.qualifier;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

interface QualifierWebClient {
    @PostExchange
    LeadQualificationResponse getQualificatorResponse(@RequestBody LeadQualificationRequest leadQualificationRequest);
}
