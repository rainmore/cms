package com.rainmore.cms.modules.core.system.security.auth

import com.rainmore.cms.domains.system.security.auth.{LoginToken, QLoginToken}

import java.lang.{String => JString}
import com.rainmore.cms.modules.core.jpa.{BaseQuerydslRepositorySupportImpl, BaseRepository}
import org.springframework.stereotype.Repository

@Repository
trait LoginTokenRepository extends BaseRepository[LoginToken, JString, QLoginToken]
    with LoginTokenRepositoryCustom

trait LoginTokenRepositoryCustom

class LoginTokenRepositoryImpl
    extends BaseQuerydslRepositorySupportImpl[LoginToken, JString, QLoginToken](classOf[LoginToken], QLoginToken.loginToken)
    with LoginTokenRepositoryCustom