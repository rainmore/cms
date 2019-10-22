package com.rainmore.cms.configs.swagger2

import com.rainmore.cms.modules.BaseModule
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.{PathSelectors, RequestHandlerSelectors}
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

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
    }

}
