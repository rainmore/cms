package com.rainmore.cms.modules.core.users.services

import com.rainmore.cms.domains.users.{Account, QAccount}

import java.lang.{Long => JLong}
import com.rainmore.cms.modules.core.jpa.{BaseQuerydslRepositorySupportImpl, BaseRepository}
import org.springframework.stereotype.Repository

@Repository
trait AccountRepository
    extends BaseRepository[Account, JLong, QAccount]
    with AccountRepositoryCustom

trait AccountRepositoryCustom

class AccountRepositoryImpl
    extends BaseQuerydslRepositorySupportImpl[Account, JLong, QAccount](classOf[Account], QAccount.account)
        with AccountRepositoryCustom
