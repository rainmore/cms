package com.rainmore.cms.modules.core.system.security.auth

import java.lang.{String => JString}
import com.rainmore.cms.domains.core.users.{LoginToken, QLoginToken}
import com.rainmore.cms.modules.core.jpa.{BaseQuerydslRepositorySupportImpl, BaseRepository}
import org.springframework.stereotype.Repository

@Repository
trait LoginTokenRepository extends BaseRepository[LoginToken, JString, QLoginToken]
    with LoginTokenRepositoryCustom

trait LoginTokenRepositoryCustom

class LoginTokenRepositoryImpl
    extends BaseQuerydslRepositorySupportImpl[LoginToken, JString, QLoginToken](classOf[LoginToken], QLoginToken.loginToken)
    with LoginTokenRepositoryCustom