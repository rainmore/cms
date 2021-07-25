package com.rainmore.cms.modules.core.system.security.auth

import com.querydsl.core.types.dsl.BooleanExpression
import com.rainmore.cms.domains.system.security.auth.QLoginToken
import com.rainmore.cms.domains.users.Account

object LoginTokenSpecification {

    def accountCondition(account: Account): BooleanExpression = usernameCondition(account.getEmail)

    def usernameCondition(username: String): BooleanExpression = QLoginToken.loginToken.account.email.eq(username)
}
