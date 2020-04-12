package com.rainmore.cms.configs.mongo

import com.rainmore.cms.configs.time.DateUtils
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.mongodb.core.convert.MongoCustomConversions

import scala.jdk.CollectionConverters._

@Configuration
class MongoConfig {

    @Bean
    def customConversions: MongoCustomConversions = {
        new MongoCustomConversions(Seq(
            new DateUtils.LocalDateTimeToInstanceConverter,
            new DateUtils.InstanceToLocalDateTimeConverter
        ).asJava)
    }

}
