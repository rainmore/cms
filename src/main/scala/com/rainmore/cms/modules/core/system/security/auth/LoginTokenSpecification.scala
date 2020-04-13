package com.rainmore.cms.modules.core.system.security.auth

import com.querydsl.core.types.dsl.BooleanExpression
import com.rainmore.cms.domains.core.users.{Account, QLoginToken}

object LoginTokenSpecification {

    def accountCondition(account: Account): BooleanExpression = usernameCondition(account.getEmail)

    def usernameCondition(username: String): BooleanExpression = QLoginToken.loginToken.account.email.eq(username)
}
