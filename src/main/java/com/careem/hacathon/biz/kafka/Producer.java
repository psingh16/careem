package com.careem.hacathon.biz.kafka;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by kumari.singh on 25/02/17.
 */
@Slf4j
@Component
public class Producer {
	@Autowired
	Logger logger;
    private KafkaProducer<String, String> producer;

    private String topic;

    @Autowired
    @Qualifier(value="producerPropertyBean")
    PropertyBean propertyBean;
    @PostConstruct
    public void Producer1() {
        Properties props = propertyBean.getProps();
        producer = new org.apache.kafka.clients.producer.KafkaProducer<String, String>(props);
        this.topic = propertyBean.getTopics().get(0);

        logger.info("Started the producer thread " + this.topic);
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
            logger.info("Sent message: (" + id + ", " + json + ")");
        } catch (Exception ex) {
           logger.error("Error in writing onto kafka queue, json : {}. Error : {}", json, ex.getMessage());
            throw ex;        }

    }
}
