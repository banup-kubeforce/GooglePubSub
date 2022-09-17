package com.kubeforce.googlepubsub;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.function.Function;

public class ProducerFunction implements Function<TrackDetail,String> {
    @Autowired
    private PubSubPublisher publisher;

    @Override
    public String apply(TrackDetail trackDetail) {
        ObjectMapper mapper = new ObjectMapper();
        String data = "";
        try {
            data = mapper.writeValueAsString(trackDetail);

            MessageEntity entity = new MessageEntity(LocalDateTime.now(), data);
            publisher.publish(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Saved data into Kinesis successfully!";
    }
}
