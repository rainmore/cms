package com.rainmore.cms.modules.users

import com.rainmore.cms.modules.BaseModule
import org.springframework.stereotype.Component

@Component("users.Module")
class Module extends BaseModule {

    override def name: String = "users"

}
