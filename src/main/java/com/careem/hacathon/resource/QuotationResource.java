package com.careem.hacathon.resource;

import io.dropwizard.hibernate.UnitOfWork;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Created by kumari.singh on 25/02/17.
 */
@SuppressWarnings({"deprecation", "unchecked"})
@Path("/v1")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class QuotationResource {

   @Path("/quotation")
   @POST
    @UnitOfWork
    public Response getQuotationDetail(Map<String, Object> data) {
       return Response.accepted("accepted").build();
   }

    }
