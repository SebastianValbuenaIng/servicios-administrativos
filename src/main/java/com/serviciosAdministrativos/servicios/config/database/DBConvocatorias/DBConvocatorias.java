package com.serviciosAdministrativos.servicios.config.database.DBConvocatorias;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "db5ManagerFactory",
        transactionManagerRef = "db5TransactionManager",
        basePackages = {
                "com.serviciosAdministrativos.servicios.domain.repositories.DBConvocatorias"
        }
)
public class DBConvocatorias {
    private final Environment environment;

    public DBConvocatorias(Environment environment) {
        this.environment = environment;
    }

    @Bean(name = "db5DataSource")
    public DataSource dataSource5() {
        return DataSourceBuilder.create()
                .url(environment.getProperty("db5.datasource.jdbc.url"))
                .driverClassName(environment.getProperty("db5.datasource.driver-class-name"))
                .username(environment.getProperty("db5.datasource.username"))
                .password(environment.getProperty("db5.datasource.password"))
                .build();
    }

    @Bean(name = "db5ManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean5() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource5());
        factoryBean.setPackagesToScan("com.serviciosAdministrativos.servicios.domain.entities.DBConvocatorias");
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        factoryBean.setJpaVendorAdapter(vendorAdapter);
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.default_schema", "conv");
        properties.put("hibernate.dialect", environment.getProperty("db5.jpa.properties.hibernate.dialect"));
        factoryBean.setJpaPropertyMap(properties);
        return factoryBean;
    }

    @Bean(name = "db5TransactionManager")
    public PlatformTransactionManager platformTransactionManager5(
            @Qualifier(value = "db5ManagerFactory") EntityManagerFactory entityManagerFactory
    ) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
