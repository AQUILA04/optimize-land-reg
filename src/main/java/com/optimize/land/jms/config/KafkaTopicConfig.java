package com.optimize.land.jms.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    //TODO: add num partition config to application.yml
    @Bean
    public NewTopic afisMasterTopic() {
        return new NewTopic("afis-master-topic", 1, (short) 1);
    }

    @Bean
    public NewTopic afisMatcherTopic() {
        return new NewTopic("afis-matcher-topic", 1, (short) 1);
    }

    @Bean
    public NewTopic afisMatcherResultTopic() {
        return new NewTopic("afis-matcher-result-topic", 1, (short) 1);
    }

    @Bean
    public NewTopic afisMasterFeedbackTopic() {
        return new NewTopic("afis-master-feedback-topic", 1, (short) 1);
    }
}
