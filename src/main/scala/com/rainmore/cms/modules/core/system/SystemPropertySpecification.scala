package com.rainmore.cms.modules.core.system

import com.querydsl.core.types.dsl.BooleanExpression
import com.rainmore.cms.domains.core.system.security.QSystemProperty

object SystemPropertySpecification {

    private val qSystemProperty = QSystemProperty.systemProperty

    def idPrefixCondition(id: String): BooleanExpression = qSystemProperty.id.like("%s%%".format(id))

}
