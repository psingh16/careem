package com.careem.hacathon.biz.kafka;

import com.careem.hacathon.biz.service.QuotationService;
import com.careem.hacathon.pojos.GetQuotationRequestDTO;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

import java.util.Properties;
import java.util.concurrent.Callable;

/**
 * Created by kumari.singh on 25/02/17.
 */
@Slf4j
@Service
public class Consumer implements Callable<Object> {

    PropertyBean consumerPropertyBean;

    private KafkaConsumer<String, String> consumer;

    QuotationService quotationService;

    public Consumer(PropertyBean consumerPropertyBean, QuotationService quotationService) {

        this.consumerPropertyBean = consumerPropertyBean;
        this.quotationService = quotationService;

        Properties props = consumerPropertyBean.getProps();
        this.consumer = new KafkaConsumer<String, String>(props);
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
               // log.error("Exception in processing records. Error : {}", e.getMessage(), e);
            }
        }
    }

    public void shutdown() {
        consumer.wakeup();
    }



}

