package com.rainmore.cms

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

object Application extends App {
    val context = SpringApplication.run(classOf[Application], args: _*)
    val singletonNames: Array[String] = context.getBeanFactory.getSingletonNames
    for (singleton <- singletonNames) {
        println(singleton)
    }
}

@SpringBootApplication
class Application
