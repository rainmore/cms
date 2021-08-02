package com.rainmore.cms.modules.core.system.security.auth

import com.rainmore.cms.domains.system.security.auth.{QTest, Test}
import com.rainmore.cms.modules.core.jpa.{BaseQuerydslRepositorySupportImpl, BaseRepository}
import org.springframework.stereotype.{Component, Repository}

import scala.jdk.CollectionConverters._

@Repository("core.system.security.auth.accountRepository")
trait AccountRepository extends BaseRepository[Test, Integer, QTest]
    with AccountRepositoryCustom

trait AccountRepositoryCustom {
    def findByName(name: String): Seq[Test]
}

@Component("core.system.security.auth.accountRepositoryImpl")
class AccountRepositoryImpl
    extends BaseQuerydslRepositorySupportImpl[Test, Integer, QTest](classOf[Test], QTest.test)
        with AccountRepositoryCustom {

    override def findByName(name: String): Seq[Test] = {
        from(qEntity)
            .where(QTest.test.name.like(name))
            .fetch()
            .asScala
            .toSeq
    }
}
