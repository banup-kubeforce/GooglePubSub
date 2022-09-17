package com.kubeforce.googlepubsub;

import com.google.cloud.spring.pubsub.integration.outbound.PubSubMessageHandler;
import com.google.pubsub.v1.PubsubMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import com.google.cloud.spring.pubsub.core.PubSubTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PubSubPublisher {

    private final String topic;
    private final PubSubTemplate pubSubTemplate;
    public PubSubPublisher(
            @Value("${pubsub.topic}") String topic,
            PubSubTemplate pubSubTemplate) {
        this.topic = topic;
        this.pubSubTemplate = pubSubTemplate;
    }

    public void publish(String payload) {
        pubSubTemplate.publish(topic, payload);
    }
}