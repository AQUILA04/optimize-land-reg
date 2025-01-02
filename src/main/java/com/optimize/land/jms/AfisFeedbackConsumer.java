package com.optimize.land.jms;

import com.optimize.land.jms.model.RegistrationProcessorFeedback;
import com.optimize.land.service.ActorService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AfisFeedbackConsumer {
    private final ActorService actorService;


    @KafkaListener(topics = "afis-master-feedback-topic", groupId = "afis-master", containerFactory = "kafkaListenerContainerFactory")
    public void receiveAFISFeedback (RegistrationProcessorFeedback feedback) {
        actorService.afterMatchingOperation(feedback);
    }
}
