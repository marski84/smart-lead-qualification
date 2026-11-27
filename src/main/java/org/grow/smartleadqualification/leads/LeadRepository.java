package org.grow.smartleadqualification.leads;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface LeadRepository extends JpaRepository<Lead, Long> {
}
