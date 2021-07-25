package com.rainmore.cms.modules.core.users.services

import java.time.{LocalDateTime, Period}
import com.querydsl.core.types.dsl.BooleanExpression
import com.rainmore.cms.domains.system.security.auth.QForgetPasswordRequest

object ForgetPasswordRequestSpecification {

    def expiredCondition(period: Period): BooleanExpression = {
        QForgetPasswordRequest
            .forgetPasswordRequest
            .createdAt
            .before(LocalDateTime.now().minusDays(period.getDays))
    }

}
