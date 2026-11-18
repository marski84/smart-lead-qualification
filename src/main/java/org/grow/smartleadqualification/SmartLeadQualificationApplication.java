package org.grow.smartleadqualification;

import org.grow.smartleadqualification.qualifier.LeadQualificationRequest;
import org.grow.smartleadqualification.qualifier.QualifierFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SmartLeadQualificationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SmartLeadQualificationApplication.class, args);

        QualifierFacade qualifierFacade = run.getBean(QualifierFacade.class);
        qualifierFacade.getQualificationResponse(LeadQualificationRequest.of("openai/gpt-oss-20b",
                "Our CTO wants to evaluate your " +
                        "API for our new product. Can " +
                        "someone walk us through the " +
                        "technical capabilities?"));

    }

}
