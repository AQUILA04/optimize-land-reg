package com.optimize.land.jms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.optimize.land.jms.model.AfisMasterRequest;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AfisProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMatchingRequest(AfisMasterRequest afisMasterRequest) throws JsonProcessingException {
        kafkaTemplate.send("afis-master-topic", new ObjectMapper().writeValueAsString(afisMasterRequest) );
    }
}
