package com.rainmore.cms.modules.core.users.services

import com.rainmore.cms.domains.users.{Account, QAccount, QRole, Role}

import java.lang.{Long => JLong}
import com.rainmore.cms.modules.core.jpa.{BaseQuerydslRepositorySupportImpl, BaseRepository}
import org.springframework.stereotype.Repository

import scala.jdk.CollectionConverters._

@Repository
trait RoleRepository
    extends BaseRepository[Role, JLong, QRole]
    with RoleRepositoryCustom

trait RoleRepositoryCustom{
    def findBy(account: Account): Seq[Role]
}

class RoleRepositoryImpl
    extends BaseQuerydslRepositorySupportImpl[Role, JLong, QRole](classOf[Role], QRole.role)
        with RoleRepositoryCustom {

    private val specification = new RoleSpecification

    override def findBy(account: Account): Seq[Role] = {
        val qAccount = QAccount.account
        val criteria = specification.isActualCondition.and(qAccount.eq(account))
        from(qAccount)
            .join(qAccount.roles, qEntity)
            .where(criteria)
            .orderBy(specification.defaultSort: _*)
            .select(qEntity)
            .fetch().asScala.toVector
    }

}
