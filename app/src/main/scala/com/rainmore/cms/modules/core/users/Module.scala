package com.rainmore.cms.modules.core.users

import com.rainmore.cms.modules.BaseModule
import org.springframework.stereotype.Component

@Component("core.users.Module")
class Module extends BaseModule {

    override def name: String = "users"

}
