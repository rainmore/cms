package com.rainmore.cms.configs.apis

import java.util.Locale

import com.rainmore.cms.configs.time.DateUtils
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.domain.PageRequest
import org.springframework.data.web.PageableHandlerMethodArgumentResolver
import org.springframework.format.FormatterRegistry
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.LocaleResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.i18n.SessionLocaleResolver

@Configuration
class ApiConfig extends WebMvcConfigurer {

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
        resolver.setFallbackPageable(PaginationConfig.defaultPageRequest)
        resolver.setPrefix("_")
        argumentResolvers.add(resolver)
    }

    override def addFormatters(registry: FormatterRegistry): Unit = {
        super.addFormatters(registry)
        registry.addConverter(new DateUtils.StringToLocalDateConverter)
        registry.addConverter(new DateUtils.StringToLocalDateTimeConverter)
        registry.addConverter(new DateUtils.StringToLocalTimeConverter)
    }

    @Bean def paginationConfig: PaginationConfig = defaultPaginationConfig

    @Bean
    def localeResolver: LocaleResolver = {
        val ret: SessionLocaleResolver = new SessionLocaleResolver
        ret.setDefaultLocale(new Locale("en"))
        ret
    }

    //    @Bean
    //    @Inject
    //    def messageSource(messagesService: MessagesService): MessageSource = new AbstractMessageSource {
    //        override def resolveCode(code: String, locale: Locale): MessageFormat = {
    //            val message = messagesService.findOne(code, locale).map(_.getMessage)
    //            createMessageFormat(message.orNull, locale)
    //        }
    //    }


}
