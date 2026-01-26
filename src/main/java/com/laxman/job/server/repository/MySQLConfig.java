//package com.laxman.job.server.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//import jakarta.persistence.EntityManagerFactory;
//
//@Configuration
//@EnableJpaRepositories(
//        basePackages = "com.laxman.job.server.mysql.repository",
//        entityManagerFactoryRef = "mysqlEntityManagerFactory",
//        transactionManagerRef = "mysqlTransactionManager"
//)
//public class MySQLConfig {
//
//    @Bean(name = "mysqlDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DataSource mysqlDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean(name = "mysqlEntityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
//            JpaProperties jpaProperties) {
//
//        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
//        emf.setDataSource(mysqlDataSource());
//        emf.setPackagesToScan("com.laxman.job.server.mysql.model");
//        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
//        emf.setJpaPropertyMap(jpaProperties.getProperties());
//
//        return emf;
//    }
//
//    @Bean(name = "mysqlTransactionManager")
//    public JpaTransactionManager mysqlTransactionManager(
//            @Qualifier("mysqlEntityManagerFactory") EntityManagerFactory emf) {
//
//        return new JpaTransactionManager(emf);
//    }
//}
