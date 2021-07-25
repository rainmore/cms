package com.rainmore.cms.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeController {
    @GetMapping(path = Array("/", "/webapp/**"))
    def index: String = "forward:/index.html"
}
