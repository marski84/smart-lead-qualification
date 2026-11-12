package org.grow.smartleadqualification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SmartLeadQualificationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SmartLeadQualificationApplication.class, args);

//        QualifierFacade qualifierFacade = run.getBean(QualifierFacade.class);
//        qualifierFacade.getQualificationResponse(new LeadQualificationRequest("openai/gpt-oss-20b", "data"));

    }

}
