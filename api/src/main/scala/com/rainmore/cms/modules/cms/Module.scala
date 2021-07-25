package com.rainmore.cms.modules.cms

import com.rainmore.cms.modules.core.jpa.BaseModule
import org.springframework.stereotype.Component

@Component("cms.Module")
class Module extends BaseModule {

    override val name: String = "cms"

}
