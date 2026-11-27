package org.grow.smartleadqualification.leads;

import java.util.Arrays;
import java.util.List;

class LeadConverter {

    public static LeadReportDto toReportDto(Lead lead) {
        return new LeadReportDto(
                lead.getId(),
                lead.getTitle(),
                lead.getType(),
                lead.getUrgency(),
                lead.getSummary()
        );
    }

    public static Lead fromQualifier(Long messageId, String lead) {
        List<String> paramList = Arrays.stream(lead.split(";", 5))
                .map(String::trim)
                .toList();

        return Lead.builder()
                .messageId(messageId)
                .title(paramList.get(1))
                .type(LeadType.getLeadType(paramList.get(2)))
                .urgency(Urgency.getUrgency(paramList.get(3)))
                .summary(paramList.getLast())
                .build();
    }
}
