package com.careem.hacathon.bootstrap;

import com.careem.hacathon.dao.GenericAbstractDAO;
import com.careem.hacathon.dao.Price;
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

/**
 * Created by kumari.singh on 25/02/17.
 */
public class Main extends Application<AppConfiguration> {

    private final HibernateBundle<AppConfiguration> hibernateBundle = new HibernateBundle<AppConfiguration>(
            Price.class
    ) {

        public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
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
            @Override
            public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        bootstrap.addBundle(hibernateBundle);

    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {
        GenericAbstractDAO<Price> priceGenericAbstractDAO =
                new GenericAbstractDAO<Price>(hibernateBundle.getSessionFactory(), "Price");

        environment.jersey().register(new QuotationResource());

    }
}
