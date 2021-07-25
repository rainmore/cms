package com.rainmore.cms.modules.core.system

import com.rainmore.cms.modules.core.jpa.BaseModule
import org.springframework.stereotype.Component

@Component("core.system.Module")
class Module extends BaseModule {

    override val name: String = "system"

}
