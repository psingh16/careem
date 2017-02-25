package com.careem.hacathon.biz.kafka;

import com.careem.hacathon.pojos.GetQuotationRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Properties;
import java.util.concurrent.Callable;

/**
 * Created by kumari.singh on 25/02/17.
 */
@Slf4j
public class Consumer implements Callable<Object> {

    PropertyBean consumerPropertyBean;

    private KafkaConsumer<String, String> consumer;

    private ObjectMapper objectMapper;

    public Consumer(PropertyBean consumerPropertyBean) {

        this.consumerPropertyBean = consumerPropertyBean;


        Properties props = consumerPropertyBean.getProps();
        this.consumer = new KafkaConsumer<String, String>(props);

        objectMapper = new ObjectMapper();

       // log.info("Started the consumer thread " + consumerPropertyBean.getTopics().toString());
    }

    public Object call() throws Exception {
        consumer.subscribe(consumerPropertyBean.getTopics());

        while (true) {
            try {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                for (ConsumerRecord<String, String> record : records) {
                    GetQuotationRequestDTO getQuotationRequestDTO = new Gson().fromJson(record.value(), GetQuotationRequestDTO.class);
                   // System.out.print("priyanka "+getQuotationRequestDTO.getBusinessId());

                    }
            } catch (Exception e) {
               // log.error("Exception in processing records. Error : {}", e.getMessage(), e);
            }
        }
    }

    public void shutdown() {
        consumer.wakeup();
    }



}

