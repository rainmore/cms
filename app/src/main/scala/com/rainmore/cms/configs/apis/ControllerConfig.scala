package com.rainmore.cms.configs.apis

import java.lang.{String => JString}

import org.springframework.beans.propertyeditors.StringTrimmerEditor
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.{ControllerAdvice, InitBinder}

@ControllerAdvice
class ControllerConfig {

    @InitBinder
    def initBinder(binder: WebDataBinder): Unit = {
        val stringTrimmerEditor = new StringTrimmerEditor(true)
        binder.registerCustomEditor(classOf[String], stringTrimmerEditor)
        binder.registerCustomEditor(classOf[JString], stringTrimmerEditor)
    }
}
