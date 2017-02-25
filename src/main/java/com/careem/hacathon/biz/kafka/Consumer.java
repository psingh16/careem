package com.careem.hacathon.biz.kafka;

import com.careem.hacathon.biz.service.QuotationService;
import com.careem.hacathon.pojos.GetQuotationRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;
import java.util.concurrent.Callable;

/**
 * Created by kumari.singh on 25/02/17.
 */
@Component
public class Consumer implements Callable<Object> {

	@Autowired
	Logger logger;
    
    private KafkaConsumer<String, String> consumer;

    @SuppressWarnings("unused")
	private ObjectMapper objectMapper;

    @Autowired
    QuotationService quotationService;

    @Autowired
    @Qualifier(value="consumerPropertyBean")
    PropertyBean consumerPropertyBean;
    @PostConstruct
    public void Consumer1() {

        Properties props = consumerPropertyBean.getProps();
        this.consumer = new KafkaConsumer<String, String>(props);

        objectMapper = new ObjectMapper();

       logger.info("Started the consumer thread " + consumerPropertyBean.getTopics().toString());
    }

    public Object call() throws Exception {
        consumer.subscribe(consumerPropertyBean.getTopics());

        while (true) {
            try {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                for (ConsumerRecord<String, String> record : records) {
                    GetQuotationRequestDTO getQuotationRequestDTO = new Gson().fromJson(record.value(), GetQuotationRequestDTO.class);
                    quotationService.updateBooking(getQuotationRequestDTO);

                    }
            } catch (Exception e) {
                logger.error("Exception in processing records. Error : {}", e.getMessage(), e);
            }
        }
    }

    public void shutdown() {
        consumer.wakeup();
    }



}

