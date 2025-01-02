package com.optimize.land.jms;

import com.optimize.land.jms.model.AfisMasterRequest;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AfisProducer {
    private final KafkaTemplate<String, AfisMasterRequest> kafkaTemplate;

    public void sendMatchingRequest(AfisMasterRequest afisMasterRequest) {
        kafkaTemplate.send("afis-master-topic", afisMasterRequest);
    }
}
