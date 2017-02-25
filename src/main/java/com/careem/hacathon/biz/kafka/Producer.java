package com.careem.hacathon.biz.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * Created by kumari.singh on 25/02/17.
 */
@Slf4j
public class Producer {
    private static final String errorMsg = "Oops!! Your request could not be completed";

    private KafkaProducer<String, String> producer;

    private String topic;

    public Producer(PropertyBean propertyBean) {
        Properties props = propertyBean.getProps();
        producer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(props);
        this.topic = propertyBean.getTopics().get(0);

        log.info("Started the producer thread " + this.topic);
    }

    /**
     * Writes to Kafka queue
     * @param id
     *      id
     * @param json
     *      message
     */
    public void write(String id, String json) throws Exception {
        try {
            producer.send(new ProducerRecord<String, String>(topic, id, json)).get();
            log.info("Sent message: (" + id + ", " + json + ")");
        } catch (Exception ex) {
            log.error("Error in writing onto kafka queue, json : {}. Error : {}", json, ex.getMessage());
            throw ex;        }

    }
}
