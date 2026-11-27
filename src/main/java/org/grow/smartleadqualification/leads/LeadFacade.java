package org.grow.smartleadqualification.leads;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LeadFacade {
    private final LeadCommandService leadCommandService;
    private final LeadQueryService leadQueryService;

    public void registerLead(String qualifierData) {
        leadCommandService.saveLead(qualifierData);
    }

    public LeadReportDto getLeadById(Long id) {
        return leadQueryService.findById(id);
    }

    public Page<LeadReportDto> getAllLeads(Pageable pageable) {
        return leadQueryService.getAll(pageable);
    }


}
