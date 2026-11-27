package org.grow.smartleadqualification.leads;

import lombok.AllArgsConstructor;
import org.grow.smartleadqualification.leads.exception.LeadNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
class LeadQueryServiceImpl implements LeadQueryService {

    private final LeadRepository leadRepository;

    @Override

    public LeadReportDto findById(final Long id) {
        Lead lead = leadRepository.findById(id).orElseThrow(() -> new LeadNotFoundException(id));
        return LeadConverter.toReportDto(lead);
    }

    @Override
    public Page<LeadReportDto> getAll(final Pageable pageable) {
        return leadRepository.findAll(pageable).map(LeadConverter::toReportDto);
    }
}
