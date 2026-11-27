package org.grow.smartleadqualification.leads;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "leads", indexes = {
        @Index(name = "idx_lead_id_unq", columnList = "id", unique = true)
})
class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String title;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LeadType type;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Urgency urgency;

    @NotEmpty
    private String summary;

    @NotNull
    private Long messageId;


}
