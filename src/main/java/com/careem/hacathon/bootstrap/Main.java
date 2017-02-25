package com.careem.hacathon.bootstrap;

import com.careem.hacathon.biz.kafka.Consumer;
import com.careem.hacathon.biz.kafka.Producer;
import com.careem.hacathon.dao.GenericAbstractDAO;
import com.careem.hacathon.dao.model.Booking;
import com.careem.hacathon.dao.model.Price;
import com.careem.hacathon.dao.model.Warehouse;
import com.careem.hacathon.resource.QuotationResource;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by kumari.singh on 25/02/17.
 */
public class Main extends Application<AppConfiguration> {
    private static ClassPathXmlApplicationContext context = null;

    private final HibernateBundle<AppConfiguration> hibernateBundle = new HibernateBundle<AppConfiguration>(
            Price.class,
            Booking.class,
            Warehouse.class
    ) {

        public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        context = new ClassPathXmlApplicationContext("applicationContext.xml", "datastore-mysql.xml");
        new Main().run(args);
    }

    @Override
    public String getName() {
        return "Driver Performance Engine - GUI and Data Service";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        // Enable variable substitution with environment variables
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));

        bootstrap.addBundle(new AssetsBundle("/assets/html", "/", "index.html"));

        bootstrap.addBundle(new MigrationsBundle<AppConfiguration>() {
            public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);
        startKafkaConsumer();

    }


    public void run(AppConfiguration configuration, Environment environment) {


        environment.jersey().register(new QuotationResource(context.getBean(Producer.class)));

    }

    private void startKafkaConsumer() {
        final ExecutorService executor = Executors.newFixedThreadPool(2);

        final List<Consumer> consumers = new ArrayList<Consumer>();
        for (int i = 0; i < 2; i++) {
            Consumer consumer = context.getBean(Consumer.class);
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
}
