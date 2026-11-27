package org.grow.smartleadqualification.leads;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class LeadCommandServiceImpl implements LeadCommandService {

    private final LeadRepository leadRepository;

    @Override
    public Lead saveLead(Long messageId, String lead) {
        Lead newLead = LeadConverter.fromQualifier(messageId, lead);
        return leadRepository.save(newLead);
    }
}
