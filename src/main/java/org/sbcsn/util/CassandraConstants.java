package org.sbcsn.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;

/**
 * Created by bapaydin on 16.02.2017.
 */

@Configuration
@PropertySource(value = {"classpath:cassandra-config.properties"})
public class CassandraConstants {

    private static final String KEYSPACE = "cassandra.keyspace";
    private static final String CONTACTPOINTS = "cassandra.contactpoints";
    private static final String PORT = "cassandra.port";


    @Autowired
    private Environment environment;

    private String getKeyspaceName() {
        return environment.getProperty(KEYSPACE);
    }


    private String getContactpoints() {
        return environment.getProperty(CONTACTPOINTS);
    }


    private int getPort() {
        return Integer.parseInt(environment.getProperty(PORT));
    }


    @Bean
    public CassandraClusterFactoryBean clusterFactoryBean() {
        CassandraClusterFactoryBean cassandraClusterFactoryBean = new CassandraClusterFactoryBean();
        cassandraClusterFactoryBean.setContactPoints(getContactpoints());
        cassandraClusterFactoryBean.setPort(getPort());
        return cassandraClusterFactoryBean;
    }

    @Bean
    public CassandraMappingContext cassandraMappingContext() {
        return new BasicCassandraMappingContext();
    }

    @Bean
    public CassandraConverter cassandraConverter() {
        return new MappingCassandraConverter(cassandraMappingContext());
    }


    @Bean
    public CassandraSessionFactoryBean sessionFactoryBean() throws Exception {
        CassandraSessionFactoryBean cassandraSessionFactoryBean = new CassandraSessionFactoryBean();
        cassandraSessionFactoryBean.setCluster(clusterFactoryBean().getObject());
        cassandraSessionFactoryBean.setKeyspaceName(getKeyspaceName());
        cassandraSessionFactoryBean.setConverter(cassandraConverter());
        cassandraSessionFactoryBean.setSchemaAction(SchemaAction.NONE);
        return cassandraSessionFactoryBean;
    }

    @Bean
    public CassandraOperations cassandraOperations() throws Exception {
        return new CassandraTemplate((sessionFactoryBean().getObject()));
    }
}
