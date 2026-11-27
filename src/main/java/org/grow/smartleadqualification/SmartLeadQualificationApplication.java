package org.grow.smartleadqualification;

import lombok.extern.slf4j.Slf4j;
import org.grow.smartleadqualification.leads.LeadFacade;
import org.grow.smartleadqualification.qualifier.QualifierFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class SmartLeadQualificationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SmartLeadQualificationApplication.class, args);

        QualifierFacade qualifierFacade = run.getBean(QualifierFacade.class);
        LeadFacade leadFacade = run.getBean(LeadFacade.class);
        String qualificationResponse = qualifierFacade.qualify(
                "Our CTO wants to evaluate your " +
                        "API for our new product. Can " +
                        "someone walk us through the " +
                        "technical capabilities?");

        log.warn(qualificationResponse);

        leadFacade.registerLead(3L, qualificationResponse);


    }

}

