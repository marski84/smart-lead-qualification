package org.grow.smartleadqualification.leads;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

interface LeadQueryService {

    LeadReportDto findById(Long id);

    Page<LeadReportDto> getAll(Pageable pageable);
}
