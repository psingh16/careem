package com.careem.hacathon.resource;

import com.careem.hacathon.biz.kafka.Producer;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

/**
 * Created by kumari.singh on 25/02/17.
 */

@RequestMapping("/v1")
@Slf4j
@RestController
public class QuotationResource {

	@Autowired
	Producer producer;

	@RequestMapping(value="/quotation",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getQuotationDetail(@RequestBody Map<String, Object> data) throws Exception {
		String quotationId = UUID.randomUUID().toString();
		data.put("quotationId", quotationId);
		// write to kafka queue
		String json = new Gson().toJson(data);
		producer.write(quotationId, json);
		return ResponseEntity.ok("Your request is accepted. We will get back to you shortly. Please use quotation Id: "
				+ quotationId + " for future reference");

	}

}
