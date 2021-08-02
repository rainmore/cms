package com.rainmore.cms.modules.core.users.services

import com.rainmore.cms.domains.users.{Account, QAccount}

import java.lang.{Long => JLong}
import com.rainmore.cms.modules.core.jpa.{BaseQuerydslRepositorySupportImpl, BaseRepository}
import org.springframework.stereotype.{Component, Repository}

import scala.jdk.CollectionConverters._

@Repository("core.users.services.accountRepository")
trait AccountRepository
    extends BaseRepository[Account, JLong, QAccount]
    with AccountRepositoryCustom

trait AccountRepositoryCustom {
    def findByName(name: String): Seq[Account]
}

@Component("core.users.services.accountRepositoryImpl")
class AccountRepositoryImpl
    extends BaseQuerydslRepositorySupportImpl[Account, JLong, QAccount](classOf[Account], QAccount.account)
        with AccountRepositoryCustom {

    override def findByName(name: String): Seq[Account] = {
        from(QAccount.account)
            .where(QAccount.account.firstName.like(name).or(QAccount.account.lastName.like(name)))
            .fetch()
            .asScala
            .toSeq
    }
}
