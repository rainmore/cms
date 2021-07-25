package com.rainmore.config

import com.rainmore.BaseSpec
import com.rainmore.cms.Application
import com.typesafe.config.{Config, ConfigFactory}
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}
import javax.sql.DataSource
import org.hibernate.cfg.AvailableSettings
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.context.annotation.{AnnotationConfigApplicationContext, Bean, ComponentScan, Configuration}
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.EnableTransactionManagement

object AppCtx {
    lazy val application: AbstractApplicationContext = new AnnotationConfigApplicationContext(
        classOf[BaseSpec].getPackage.getName
    )
}

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = Array("com.rainmore"))
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT, classes = Array(classOf[Application]))
@ActiveProfiles(profiles = Array("test"))
class AppCtx {
    @Bean
    def appConfig: Config = ConfigFactory.load("application-test.properties").withFallback(ConfigFactory.load())

    @Bean
    def dataSource(appConfig: Config): DataSource = {

        val config = new HikariConfig
        config.setJdbcUrl(appConfig.getString("spring.datasource.url"))
        config.setUsername(appConfig.getString("spring.datasource.username"))
        config.setPassword(appConfig.getString("spring.datasource.password"))
        config.setCatalog(appConfig.getString("spring.datasource.name"))


        config.setMinimumIdle(appConfig.getInt("spring.datasource.hikari.minimumIdle"))
        config.setMaximumPoolSize(appConfig.getInt("spring.datasource.hikari.maximumPoolSize"))
        config.setIdleTimeout(appConfig.getInt("spring.datasource.hikari.idleTimeout"))

        new HikariDataSource(config)
    }

    @Bean
    def entityManagerFactory(appConfig: Config, dataSource: DataSource): LocalContainerEntityManagerFactoryBean = {

        val jpaVendorAdapter = new HibernateJpaVendorAdapter
        jpaVendorAdapter.setDatabasePlatform(appConfig.getString("spring.jpa.hibernate.dialect"))
        jpaVendorAdapter.setGenerateDdl(appConfig.getBoolean("spring.jpa.generate-ddl"))
        jpaVendorAdapter.setShowSql(appConfig.getBoolean("spring.jpa.show-sql"))

        val localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean
        localContainerEntityManagerFactoryBean.setDataSource(dataSource)
        localContainerEntityManagerFactoryBean.setPackagesToScan("com.rainmore.cms.domains")
        localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter)

        localContainerEntityManagerFactoryBean.getJpaPropertyMap
            .putIfAbsent(AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS,
                appConfig.getBoolean("spring.jpa.hibernate.use-new-id-generator-mappings").toString)
        localContainerEntityManagerFactoryBean.getJpaPropertyMap
            .putIfAbsent(AvailableSettings.PHYSICAL_NAMING_STRATEGY,
                appConfig.getString("spring.jpa.hibernate.naming.physical-strategy"))

        localContainerEntityManagerFactoryBean
    }
}
