package com.careem.hacathon.resource;

import com.careem.hacathon.biz.kafka.Producer;
import com.google.gson.Gson;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;
import java.util.UUID;

/**
 * Created by kumari.singh on 25/02/17.
 */
@SuppressWarnings({"deprecation", "unchecked"})
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class QuotationResource {
    Producer producer;

    public QuotationResource(Producer producer) {
        this.producer = producer;
    }

   @Path("/quotation")
   @POST
    @UnitOfWork
    public Response getQuotationDetail(Map<String, Object> data) throws Exception {
       String quotationId = UUID.randomUUID().toString();
       //write to kafka queue
       String json = new Gson().toJson(data);
       producer.write(quotationId, json);
       return Response.accepted("Your request is accepted. We will get back to you shortly. Please use quotation Id: "+quotationId+"for future reference").build();

   }

}
