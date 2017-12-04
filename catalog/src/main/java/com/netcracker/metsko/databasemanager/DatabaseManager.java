//package com.netcracker.metsko.databasemanager;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import java.util.Properties;
//import javax.sql.DataSource;
//
//
//@Configuration
//@EnableTransactionManagement
//public class DatabaseConfig {
//
//    @Bean
//    @Qualifier("source")
//    public DataSource dataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("javax.persistence.jdbc.driver"));
//        dataSource.setUrl(env.getProperty("javax.persistence.jdbc.url"));
//        dataSource.setUsername(env.getProperty("javax.persistence.jdbc.username"));
//        dataSource.setPassword(env.getProperty("javax.persistence.jdbc.password"));
//        return dataSource;
//    }
//
//
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean entityManagerFactory =
//                new LocalContainerEntityManagerFactoryBean();
//
//        entityManagerFactory.setDataSource(dataSource);
//        entityManagerFactory.setPersistenceUnitName("CatalogPU");
//
//        // Vendor adapter
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
//
//        // Hibernate properties
//        Properties additionalProperties = new Properties();
//
//        additionalProperties.put(
//                "javax.persistence.schema-generation.database.action",
//                env.getProperty("javax.persistence.schema-generation.database.action"));
//        entityManagerFactory.setJpaProperties(additionalProperties);
//
//        return entityManagerFactory;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager() {
//        JpaTransactionManager transactionManager =
//                new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(
//                entityManagerFactory.getObject());
//        return transactionManager;
//    }
//
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }
//
//    // Private fields
//
//    @Autowired
//    private Environment env;
//
//    @Autowired
//    @Qualifier("source")
//    private DataSource dataSource;
//
//    @Autowired
//    private LocalContainerEntityManagerFactoryBean entityManagerFactory;
//
//
//    public DatabaseConfig() {
//    }
//
//
////    private static class EntityManagerHolder
////    {
////        private final static Class<? extends EntityManager> INSTANCE = entityManagerFactory.getEntityManagerInterface();
////    }
////
////    public static EntityManager getInstance()
////    {
////        return EntityManagerHolder.INSTANCE;
////    }
//}
