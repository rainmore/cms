package com.rainmore.cms.configs.swagger2

import com.rainmore.cms.modules.BaseModule
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.{PathSelectors, RequestHandlerSelectors}
import springfox.documentation.service.{ApiInfo, Contact}
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

import scala.jdk.CollectionConverters._

@Configuration
@EnableSwagger2
class Swagger2Config {

    def api: Docket = {
        new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage(classOf[BaseModule].getCanonicalName))
            .paths(PathSelectors.regex("/api.*"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo)
    }

    private def apiInfo: ApiInfo = {
        new ApiInfo(
            "CMS APIs", "", "1.0", "Terms of Service",
            new Contact("Rainmore", "rainmore.com", ""),
            "Apache License Version 2.0",
            "https://www.apache.org/licenses/",
            Set.empty.asJavaCollection
        )
    }

}
