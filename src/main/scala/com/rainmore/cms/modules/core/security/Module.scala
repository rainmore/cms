package com.rainmore.cms.modules.core.security

import com.rainmore.cms.modules.core.jpa.BaseModule
import org.springframework.stereotype.Component

@Component("core.security.Module")
class Module extends BaseModule {

    override val name: String = "security"

}
