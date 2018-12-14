package com.refresh.samples.Config;

import com.refresh.samples.model.AcmeDBProperties;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.refresh.samples.Repos")
@EnableTransactionManagement
public class EmployeeConfig {

    @ConfigurationProperties(prefix="acme.db")
    @Bean
    AcmeDBProperties dbProperties() {
       return new AcmeDBProperties();
    }

    @Bean
    DataSource dataSource(){
        return DataSourceBuilder.create()
                .driverClassName(dbProperties().getDriverClassName().trim())
                .url(dbProperties().getUrl().trim()+dbProperties().getDbname())
                .username(dbProperties().getUsername())
                .password(dbProperties().getPassword())
                .build();
    }

    DataSource hikariDataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(dbProperties().getUrl().trim()+dbProperties().getDbname());
        dataSource.setDriverClassName(dbProperties().getDriverClassName().trim());
        dataSource.setUsername(dbProperties().getUsername());
        dataSource.setPassword(dbProperties().getPassword());
        dataSource.setMaximumPoolSize(50);
        return dataSource;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        HibernateJpaVendorAdapter jpaAdapter = new HibernateJpaVendorAdapter();
        jpaAdapter.setGenerateDdl(true);
        jpaAdapter.setShowSql(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("com.refresh.samples.model");
        factoryBean.setJpaVendorAdapter(jpaAdapter);
        return factoryBean;
    }
    @Bean
    public PlatformTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }







}
