package com.rainmore.cms.modules.core.users.services

import java.lang.{Long => JLong}

import com.rainmore.cms.domains.core.users.{Account, Permission, QAccount, QPermission, QRole, Role}
import com.rainmore.cms.modules.core.jpa.{BaseQuerydslRepositorySupportImpl, BaseRepository}
import org.springframework.stereotype.Repository

import scala.jdk.CollectionConverters._

@Repository
trait PermissionRepository extends BaseRepository[Permission, JLong, QPermission]
    with PermissionRepositoryCustom

trait PermissionRepositoryCustom {
    def findBy(account: Account): Seq[Permission]

    def findBy(roles: Set[Role]): Seq[Permission]
}

class PermissionRepositoryImpl
    extends BaseQuerydslRepositorySupportImpl[Permission, JLong, QPermission](classOf[Permission], QPermission.permission)
        with PermissionRepositoryCustom {

    private val specification = new PermissionSpecification

    override def findBy(account: Account): Seq[Permission] = {
        val qAccount = QAccount.account
        val criteria = specification.isActualCondition.and(qAccount.eq(account))
        from(qAccount)
            .join(qAccount.permissions, QPermission)
            .where(criteria)
            .select(QPermission)
            .orderBy(specification.defaultSort: _*)
            .fetch().asScala.toVector
    }


    override def findBy(roles: Set[Role]): Seq[Permission] = {
        val qRole = QRole.role
        val criteria = specification.isActualCondition.and(qRole.in(roles.asJava))
        from(qRole)
            .join(qRole.permissions, QPermission)
            .where(criteria)
            .select(QPermission)
            .orderBy(specification.defaultSort: _*)
            .fetch().asScala.toVector
    }

}
