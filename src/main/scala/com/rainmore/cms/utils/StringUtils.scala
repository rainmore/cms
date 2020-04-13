package com.rainmore.cms.utils

import java.io.{InputStream, StringWriter}
import java.nio.charset.StandardCharsets
import java.lang.{String => JString}

import org.apache.commons.lang3.{StringUtils => CommonStringUtils}
import org.apache.commons.lang3.RandomStringUtils
import java.security.SecureRandom
import java.util.{Map => jMap}

import org.apache.commons.io.IOUtils

import scala.jdk.CollectionConverters._

object StringUtils {

    /**
     * Securely generates a set of random alphanumeric characters of the provided length.
     *
     * @param length The length of the resulting string.
     * @return A generated string.
     */
    def randomString(length: Int = 10): String = RandomStringUtils.random(length, 0, 0, true, true, null, new SecureRandom)

    def replaceTokens(tpl: String, tokens: Map[CharSequence, CharSequence]): String = {
        var result = tpl

        for ((token, replacement) <- tokens) {
            val target = "{%s}".format(token)
            result = result.replace(target, replacement)
        }

        result
    }

    def replaceTokensJava(tpl: String, tokens: jMap[CharSequence, CharSequence]): String = replaceTokens(tpl, tokens.asScala.toMap)

    def inputStreamToString(inputStream: InputStream): String = {
        val writer = new StringWriter()
        IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8)
        inputStream.close()
        writer.toString
    }

    def trim(string: JString): Option[String] = Option(CommonStringUtils.trimToNull(string))
}