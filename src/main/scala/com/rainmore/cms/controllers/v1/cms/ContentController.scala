package com.rainmore.cms.controllers.v1.cms

import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController => SpringRestController}

@SpringRestController("v1.cms.ContentController")
@RequestMapping(Array("/api/v1/cms/content"))
class ContentController {

    @GetMapping(Array())
    def list: Iterable[String] = ???

}
