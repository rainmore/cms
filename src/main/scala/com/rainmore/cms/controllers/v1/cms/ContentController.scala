package com.rainmore.cms.controllers.v1.cms

import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RestController => SpringRestController}

@SpringRestController("core.users.controllers.AccountController")
@RequestMapping(Array("/api/v1/users/accounts"))
class ContentController {

    @GetMapping(Array())
    def list: Iterable[String] = ???

}
