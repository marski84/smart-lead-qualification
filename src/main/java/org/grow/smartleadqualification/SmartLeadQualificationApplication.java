package org.grow.smartleadqualification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SmartLeadQualificationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(SmartLeadQualificationApplication.class, args);

//        QualifierFacade qualifierFacade = run.getBean(QualifierFacade.class);
//        LeadCommandService leadCommandService = run.getBean(LeadCommandService.class);
//        LeadQualificationResponse qualificationResponse = qualifierFacade.getQualificationResponse(LeadQualificationRequest.of("openai/gpt-oss-20b",
//                "Our CTO wants to evaluate your " +
//                        "API for our new product. Can " +
//                        "someone walk us through the " +
//                        "technical capabilities?"));
//
//        var lead = leadCommandService.saveLead(qualificationResponse.answerText());
//        System.out.println(lead);
//
//        qualifierFacade.getQualificationResponse(LeadQualificationRequest.of("openai/gpt-oss-20b",
//                "Just checking if you ship internationally."));
//        qualifierFacade.getQualificationResponse(LeadQualificationRequest.of("openai/gpt-oss-20b",
//                "I’m experiencing issues with my billing. I was charged twice this month and need this resolved ASAP.”"));
//

    }

}

