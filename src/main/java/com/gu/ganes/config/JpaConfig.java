package com.gu.ganes.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author gu.sc
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.gu.ganes.repository"})
@EnableTransactionManagement
public class JpaConfig {

  /**
   * 配置数据源
   */
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  public DataSource dataSource() {
    return DataSourceBuilder.create().build();
  }

  /**
   * 本地容器实体管理 factoryBean
   *
   * @return e
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
    jpaVendorAdapter.setGenerateDdl(false);

    LocalContainerEntityManagerFactoryBean e = new LocalContainerEntityManagerFactoryBean();
    e.setDataSource(dataSource());
    e.setJpaVendorAdapter(jpaVendorAdapter);
    e.setPackagesToScan("com.gu.ganes.entity");
    return e;
  }

  /**
   * 事务管理器
   *
   * @param entityManagerFactory 本地容器实体管理 factoryBean
   * @return 事务管理器
   */
  @Bean
  public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory);
    return transactionManager;
  }

}
