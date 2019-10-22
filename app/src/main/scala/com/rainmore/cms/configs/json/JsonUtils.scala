package com.rainmore.cms.configs.json

import java.io.IOException
import java.nio.charset.StandardCharsets
import javax.inject.Inject

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Component

@Component
class JsonUtils @Inject()
(
    objectMapper: ObjectMapper
) {

    @throws[IOException]
    def convertObjectToJsonBytes(data: Any): Array[Byte] = {
        objectMapper.writeValueAsBytes(data)
    }

    def toJson(data: Any): String = {
        if (data == null) {
            null
        }
        else {
            try {
                new String(convertObjectToJsonBytes(data), StandardCharsets.UTF_8)
            }
            catch {
                case ex: IOException =>
                    throw new RuntimeException("An error occurred while converting %s object to JSON".format(data), ex)
            }
        }
    }

    def fromJson[R](data: String, beanClass: Class[R]): R = {

        try {
            objectMapper.readValue(data, beanClass)
        }
        catch {
            case e: IOException =>
                throw new RuntimeException("An error occurred while converting data from JSON format to a bean of class: " + beanClass, e)
        }
    }

}
