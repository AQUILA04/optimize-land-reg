package com.optimize.land.jms.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic afisMasterTopic() {
        return new NewTopic("afis-master-topic", 1, (short) 2);
    }

    @Bean
    public NewTopic afisMatcherTopic() {
        return new NewTopic("afis-matcher-topic", 6, (short) 2);
    }

    @Bean
    public NewTopic afisMatcherResultTopic() {
        return new NewTopic("afis-matcher-result-topic", 3, (short) 2);
    }
}
