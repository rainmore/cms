package com.rainmore.cms.utils

import java.lang.{Long => JLong}
import java.time.{Instant, LocalDate, LocalDateTime, LocalTime, ZoneId}
import java.time.format.{DateTimeFormatter, DateTimeFormatterBuilder}
import java.util.Date

import org.springframework.core.convert.converter.Converter

object DateUtils {
    final val LocalDateFormatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    final val LocalTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_TIME

    final val MySqlLocalDateTimeFormatter: DateTimeFormatter = new DateTimeFormatterBuilder()
        .parseCaseInsensitive
        .append(LocalDateFormatter)
        .appendLiteral(' ')
        .append(LocalTimeFormatter)
        .toFormatter

    final val DefaultLocalDateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    final val zoneId = ZoneId.systemDefault()

    def toEpochMilli(localDateTime: LocalDateTime): Long = localDateTime.atZone(zoneId).toInstant.toEpochMilli

    def now: LocalDateTime = LocalDateTime.now().withNano(0)

    def asDate(localDateTime: LocalDateTime): Date = Date.from(localDateTime.atZone(zoneId).toInstant)

    def asDate(localDate: LocalDate): Date = Date.from(localDate.atStartOfDay(zoneId).toInstant)

    def asSecondsSinceEpoch(localDateTime: LocalDateTime): JLong = localDateTime.atZone(zoneId).toEpochSecond()

    def asLocalDateTime(instant: Instant): LocalDateTime = LocalDateTime.ofInstant(instant, zoneId)

    def asLocalDateTime(date: Date): LocalDateTime = asLocalDateTime(Instant.ofEpochMilli(date.getTime))

    def asLocalDateTime(timestamp: Long): LocalDateTime = asLocalDateTime(Instant.ofEpochMilli(timestamp))

    def asLocalDateTime(localDate: LocalDate): LocalDateTime = LocalDateTime.of(localDate, LocalTime.of(0, 0, 0))

    def asLocalDate(date: Date): LocalDate = Instant.ofEpochMilli(date.getTime).atZone(zoneId).toLocalDate

    class LocalDateConverter(formatter: DateTimeFormatter) extends Converter[String, LocalDate] {

        def this() = this(DateUtils.LocalDateFormatter)

        override def convert(source: String): LocalDate =  {
            if (source == null || source.isEmpty()) null
            else LocalDate.parse(source, formatter)
        }
    }

    class LocalTimeConverter(formatter: DateTimeFormatter) extends Converter[String, LocalTime] {

        def this() = this(DateUtils.LocalTimeFormatter)

        override def convert(source: String): LocalTime =  {
            if (source == null || source.isEmpty()) null
            else LocalTime.parse(source, formatter)
        }
    }

    class LocalDateTimeConverter(formatter: DateTimeFormatter) extends Converter[String, LocalDateTime] {

        def this() = this(DateUtils.DefaultLocalDateTimeFormatter)

        override def convert(source: String): LocalDateTime = {
            if (source == null || source.isEmpty()) null
            else LocalDateTime.parse(source, formatter)
        }
    }
}