/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.spring.config;

import com.example.spark.config.SparkWebConfig;
import com.example.spark.spring.domain.Customer;
import com.example.spark.spring.repository.CustomerRepository;
import com.example.spark.spring.repository.OrganisationRepository;
import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author aleksandar
 */
@Configuration
public class DatabaseConfig {

    private static final Logger logger = LoggerFactory.getLogger(SparkWebConfig.class);
    
    @Autowired
    CustomerRepository customerRepository;
    
    @Autowired
    OrganisationRepository organisationRepository;
    
    @PostConstruct
    public void setupRoutes() {
        initDemo();
    }
    
    @Bean
    public DataSource dataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.HSQL).build();
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.example.spark.spring.domain");
        factory.setDataSource(dataSource());
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory());
        return txManager;
    }
    
    public void initDemo() {
        // save a couple of customers
        customerRepository.save(new Customer("Jack", "Bauer"));
        customerRepository.save(new Customer("Chloe", "O'Brian"));
        customerRepository.save(new Customer("Kim", "Bauer"));
        customerRepository.save(new Customer("David", "Palmer"));
        customerRepository.save(new Customer("Michelle", "Dessler"));
        if (true) {
            return;
        }
        // fetch all customers
        logger.info("Customers found with findAll():");
        logger.info("-------------------------------");
        for (Customer customer : customerRepository.findAll()) {
            logger.info(customer.toString());
        }
        logger.info("");

        // fetch an individual customer by ID
        Customer customer = customerRepository.findOne(1L);
        logger.info("Customer found with findOne(1L):");
        logger.info("--------------------------------");
        logger.info(customer.toString());
        logger.info("");

        // fetch customers by last name
        logger.info("Customer found with findByLastName('Bauer'):");
        logger.info("--------------------------------------------");
        for (Customer bauer : customerRepository.findByLastName("Bauer")) {
            logger.info(bauer.toString());
        }
        logger.info("");
    }
    
}
