package com.rainmore.cms.modules.core.users

import com.rainmore.cms.domains.system.{QTest, Test}
import com.rainmore.cms.modules.core.jpa.{BaseQuerydslRepositorySupportImpl, BaseRepository}
import org.springframework.stereotype.{Component, Repository}

import scala.jdk.CollectionConverters._

@Repository("core.users.accountRepository")
trait AccountRepository extends BaseRepository[Test, Integer, QTest]
    with AccountRepositoryCustom

trait AccountRepositoryCustom {
    def findByName(name: String): Seq[Test]
}

@Component("core.users.accountRepositoryImpl")
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
