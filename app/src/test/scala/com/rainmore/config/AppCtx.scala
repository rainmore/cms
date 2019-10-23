package com.rainmore.config

import com.rainmore.BaseSpec
import com.typesafe.config.{Config, ConfigFactory}
import org.springframework.context.annotation.{AnnotationConfigApplicationContext, Bean, ComponentScan, Configuration}
import org.springframework.context.support.AbstractApplicationContext
import org.springframework.transaction.annotation.EnableTransactionManagement

object AppCtx {
    lazy val application: AbstractApplicationContext = new AnnotationConfigApplicationContext(
        classOf[BaseSpec].getPackage.getName
    )
}

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = Array(classOf[BaseSpec].getPackage.getName))
class AppCtx {
    @Bean
    def appConfig: Config = ConfigFactory.load()

}