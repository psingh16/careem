package com.careem.hacathon.bootstrap;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by kumari.singh on 25/02/17.
 */
@Data
public class AppConfiguration extends Configuration {

        @Valid
        @NotNull
        private DataSourceFactory database = new DataSourceFactory();


        @JsonProperty("database")
        public DataSourceFactory getDataSourceFactory() {
                return database;
        }

}

