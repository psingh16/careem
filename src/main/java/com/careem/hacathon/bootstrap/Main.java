package com.careem.hacathon.bootstrap;

import com.careem.hacathon.biz.kafka.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by kumari.singh on 26/02/17.
 */
@SpringBootApplication
@ImportResource(value="applicationContext.xml")
@EnableJpaRepositories(basePackages="com.careem.hacathon.*")
@EntityScan(basePackages="com.careem.hacathon.*")
@EnableScheduling
public class Main implements CommandLineRunner {
    @Autowired
    Consumer consumer;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    @Scope("prototype")
    Logger logger(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
    }

    private void startKafkaConsumer() {
        final ExecutorService executor = Executors.newFixedThreadPool(1);

        final List<Consumer> consumers = new ArrayList<Consumer>();
        for (int i = 0; i < 1; i++) {
            consumers.add(consumer);
            executor.submit(consumer);
        }

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                for (Consumer consumer : consumers) {
                    consumer.shutdown();
                }
                executor.shutdown();
                try {
                    executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void run(String... strings) throws Exception {
        startKafkaConsumer();
    }
}


