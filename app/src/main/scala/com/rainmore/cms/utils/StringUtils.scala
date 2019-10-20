package com.rainmore.cms.utils

import java.util.{Map => JMap}
import java.io.{InputStream, StringWriter}
import java.nio.charset.StandardCharsets
import java.security.SecureRandom

import org.apache.commons.io.IOUtils
import org.apache.commons.lang3.RandomStringUtils

import scala.jdk.CollectionConverters._

object StringUtils {
    private val DefaultLength = 10
    private val LineStart = 0
    /**
     * Securely generates a set of random alphanumeric characters of the provided length.
     *
     * @param length The length of the resulting string.
     * @return A generated string.
     */
    def randomString(length: Int = DefaultLength): String = RandomStringUtils.random(length, LineStart, LineStart, true, true, null, new SecureRandom)

    def replaceTokens(tpl: String, tokens: Map[CharSequence, CharSequence]): String = {
        var result = tpl

        for ((token, replacement) <- tokens) {
            val target = "{%s}".format(token)
            result = result.replace(target, replacement)
        }

        result
    }

    def replaceTokensJava(tpl: String, tokens: JMap[CharSequence, CharSequence]): String = replaceTokens(tpl, tokens.asScala.toMap)

    def inputStreamToString(inputStream: InputStream): String = {
        val writer = new StringWriter()
        IOUtils.copy(inputStream, writer, StandardCharsets.UTF_8)
        writer.toString
    }

}
