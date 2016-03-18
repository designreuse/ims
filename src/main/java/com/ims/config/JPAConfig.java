package com.ims.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.dozer.DozerBeanMapper;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

import java.beans.PropertyVetoException;
import java.util.Arrays;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages ={"com.ims.repository"})
public class JPAConfig {

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		try {
			dataSource.setDriverClass(environment.getRequiredProperty("persistence.driverClass"));
		} catch (IllegalStateException | PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dataSource.setJdbcUrl(environment.getRequiredProperty("persistence.jdbcurl"));
		dataSource.setUser(environment.getRequiredProperty("persistence.dbuser"));
		dataSource.setPassword(environment.getRequiredProperty("persistence.dbpass"));

		dataSource.setMaxPoolSize(environment.getRequiredProperty("connection.maxSize", int.class));
		dataSource.setMaxStatements(environment.getRequiredProperty("connection.maxStatements", int.class));
		dataSource.setMaxStatementsPerConnection(environment.getRequiredProperty("connection.maxStatementsPerConnection",
				int.class));
		dataSource.setNumHelperThreads(environment.getRequiredProperty("connection.numHelperThreads", int.class));
		dataSource.setMaxPoolSize(environment.getRequiredProperty("connection.maxPoolSize", int.class));
		dataSource.setMaxIdleTime(environment.getRequiredProperty("connection.maxIdleTime", int.class));
		dataSource.setMaxIdleTimeExcessConnections(environment.getRequiredProperty("connection.maxIdleTimeExcessConnections",
				int.class));

		return dataSource;
	}

	@Bean
	public JpaTransactionManager transactionManager() throws ClassNotFoundException {
		JpaTransactionManager transactionManager = new JpaTransactionManager();

		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

		return transactionManager;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws ClassNotFoundException {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan(new String[] { "com.ims.domain.entity"});
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

		entityManagerFactoryBean.setJpaProperties(hibernateProperties());

		return entityManagerFactoryBean;
	}

	private Properties hibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("persistence.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("persistence.showSql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("persistence.hbm2ddl.auto"));
		properties.put("hibernate.ejb.naming_strategy", "com.ims.domain.cfg.EMCustomNamingStrategy");
		properties.put("hibernate.connection.useUnicode", "true");
		properties.put("hibernate.connection.characterEncoding", "UTF-8");

		return properties;
	}

	@Bean
	public DozerBeanMapper mapper() {
		return new DozerBeanMapper(Arrays.asList("dozer-global-configuration.xml", "dozer-mapping.xml"));
	}
}
