package com.rainmore.cms.configs.swagger2

import com.rainmore.cms.modules.core.jpa.BaseModule
import org.springframework.context.annotation.Configuration

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
