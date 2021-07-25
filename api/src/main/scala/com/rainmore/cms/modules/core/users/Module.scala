package com.rainmore.cms.modules.core.users

import com.rainmore.cms.modules.core.jpa.BaseModule
import org.springframework.stereotype.Component

@Component("core.users.Module")
class Module extends BaseModule {

    override val name: String = "users"

}
