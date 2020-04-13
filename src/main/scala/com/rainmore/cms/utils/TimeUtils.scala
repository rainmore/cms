package com.rainmore.cms.utils

import java.time.format.{DateTimeFormatter, DateTimeFormatterBuilder}
import java.time._
import java.util.{Date, Locale}
import java.lang.{Long => JLong}

import org.springframework.core.convert.converter.Converter
import org.springframework.format.Formatter

object TimeUtils {

    private final val LocalDateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    private final val LocalTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    private final val LocalDateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    final val MySqlLocalDateTimeFormatter: DateTimeFormatter = new DateTimeFormatterBuilder()
        .parseCaseInsensitive
        .append(DateTimeFormatter.ISO_LOCAL_DATE)
        .appendLiteral(' ')
        .append(DateTimeFormatter.ISO_LOCAL_TIME)
        .toFormatter

    final val zoneId = ZoneId.systemDefault()

    def now: LocalDateTime = LocalDateTime.now().withNano(0)

    def asDate(localDateTime: LocalDateTime): Date = Date.from(localDateTime.atZone(zoneId).toInstant)

    def asDate(localDate: LocalDate): Date = Date.from(localDate.atStartOfDay(zoneId).toInstant)

    def asSecondsSinceEpoch(localDateTime: LocalDateTime): JLong = localDateTime.atZone(zoneId).toEpochSecond()

    def asLocalDateTime(instant: Instant): LocalDateTime = LocalDateTime.ofInstant(instant, zoneId)

    def asLocalDateTime(date: Date): LocalDateTime = asLocalDateTime(Instant.ofEpochMilli(date.getTime))

    def asLocalDateTime(timestamp: Long): LocalDateTime = asLocalDateTime(Instant.ofEpochMilli(timestamp))

    def asLocalDateTime(localDate: LocalDate): LocalDateTime = LocalDateTime.of(localDate, LocalTime.of(0, 0, 0))

    def asLocalDate(date: Date): LocalDate = Instant.ofEpochMilli(date.getTime).atZone(zoneId).toLocalDate

    def format(localDateTime: LocalDateTime): String = localDateTime.format(LocalDateTimeFormatter)

    def format(localDate: LocalDate): String = localDate.format(LocalDateFormatter)

    def format(localTime: LocalTime): String = localTime.format(LocalTimeFormatter)

    class LocalDateConverter extends Converter[String, LocalDate] {
        override def convert(source: String): LocalDate =  {
            if (source == null || source.isEmpty()) null
            else LocalDate.parse(source, LocalDateFormatter)
        }
    }

    class LocalTimeConverter extends Converter[String, LocalTime] {
        override def convert(source: String): LocalTime =  {
            if (source == null || source.isEmpty()) null
            else LocalTime.parse(source, LocalTimeFormatter)
        }
    }

    class LocalDateTimeConverter extends Converter[String, LocalDateTime] {
        override def convert(source: String): LocalDateTime = {
            if (source == null || source.isEmpty()) null
            else LocalDateTime.parse(source, LocalDateTimeFormatter)
        }
    }

    class SpringLocalDateFormatter extends Formatter[LocalDate] {

        override def print(localDate: LocalDate, locale: Locale): String = localDate.format(LocalDateFormatter)

        override def parse(text: String, locale: Locale): LocalDate = LocalDate.parse(text, LocalDateFormatter)
    }

    class SpringLocalTimeFormatter extends Formatter[LocalTime] {
        override def print(localTime: LocalTime, locale: Locale): String = localTime.format(LocalTimeFormatter)

        override def parse(text: String, locale: Locale): LocalTime = LocalTime.parse(text, LocalTimeFormatter)
    }

    class SpringLocalDateTimeFormatter extends Formatter[LocalDateTime] {
        override def print(localDateTime: LocalDateTime, locale: Locale): String = localDateTime.format(LocalDateTimeFormatter)

        override def parse(text: String, locale: Locale): LocalDateTime = LocalDateTime.parse(text, LocalDateTimeFormatter)
    }
}
