package com.rainmore.cms.configs.web

import java.util.Locale

import com.rainmore.cms.utils.TimeUtils
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.domain.PageRequest
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.format.FormatterRegistry
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.SessionLocaleResolver

object WebConfig {
    val MAX_FILE_UPLOAD_SIZE_IN_MB: Integer = 20
}

@Configuration
class WebConfig extends WebMvcConfigurer {

    private val defaultPaginationConfig: PaginationConfig = PaginationConfig()

//    override def getMessageCodesResolver: MessageCodesResolver = new MessageCodesResolver {
//        def resolveMessageCodes (errorCode: String, objectName: String, field: String, fieldType: Class[_] ): Array[String] = {
//            Array[String] (errorCode)
//        }
//
//        def resolveMessageCodes (errorCode: String, objectName: String): Array[String] = Array[String] (errorCode)
//    }

    override def addArgumentResolvers(argumentResolvers: java.util.List[HandlerMethodArgumentResolver]): Unit = {
        val resolver: PageableHandlerMethodArgumentResolver = new PageableHandlerMethodArgumentResolver
        resolver.setFallbackPageable(PageRequest.of(0, defaultPaginationConfig.defaultPage))
        resolver.setPrefix("_")
        argumentResolvers.add(resolver)
    }

    override def addFormatters(registry: FormatterRegistry): Unit = {
        super.addFormatters(registry)
        registry.addConverter(new TimeUtils.LocalDateConverter)
        registry.addConverter(new TimeUtils.LocalDateTimeConverter)
        registry.addConverter(new TimeUtils.LocalTimeConverter)
    }

    @Bean def paginationConfig: PaginationConfig = defaultPaginationConfig

    @Bean
    def localeResolver: LocaleResolver = {
        val ret: SessionLocaleResolver = new SessionLocaleResolver
        ret.setDefaultLocale(new Locale("en"))
        ret
    }

//    @Bean
//    def servletContainer: ServletWebServerFactory = {
//        val factory: JettyServletWebServerFactory = new JettyServletWebServerFactory
//        // mapped error pages
//        factory.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400"))
//        factory.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/error/403"))
//        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"))
//        factory.addErrorPages(new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/error/405"))
//        factory.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error"))
//        factory
//    }

//    @Bean
//    @Inject
//    def messageSource(messagesService: MessagesService): MessageSource = new AbstractMessageSource {
//        override def resolveCode(code: String, locale: Locale): MessageFormat = {
//            val message = messagesService.findOne(code, locale).map(_.getMessage)
//            createMessageFormat(message.orNull, locale)
//        }
//    }

}