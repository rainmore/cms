package com.rainmore.cms.modules.core.users.services

import java.lang.{Long => JLong}
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.rainmore.cms.domains.users.{Permission, QAccount, QPermission, QRole}
import com.rainmore.cms.domains.users.{Permission, QAccount, QPermission, QRole}
import com.rainmore.cms.modules.core.jpa.{BaseSpecification, CollectionJoinPathDescriptor}

import scala.jdk.CollectionConverters._

class PermissionSpecification extends BaseSpecification[Permission, JLong, QPermission] (QPermission.permission) {

    def defaultSort: Array[OrderSpecifier[_ <: Comparable[_]]] = Array(qEntity.name.asc())

    override def idCondition(id: JLong): BooleanExpression = qEntity.id.eq(id)

    override def archivedCondition(archived: Boolean): BooleanExpression = {
        if (archived) qEntity.archivedAt.isNotNull
        else qEntity.archivedAt.isNull
    }

    def nameCondition(name: String, id: Option[JLong]): BooleanExpression = {
        val permission = QPermission.permission
        id match {
            case None => permission.name.eq(name)
            case Some(id) => permission.name.eq(name).and(permission.id.ne(id))
        }
    }

    def parentCondition(isParent: Boolean): BooleanExpression = {
        require(Option(isParent).isDefined)
        if (isParent) qEntity.parent.isNull
        else qEntity.parent.isNotNull
    }

    def accountPermissionsJoinCondition: CollectionJoinPathDescriptor[java.util.Set[Permission], Permission] = {
        CollectionJoinPathDescriptor(qEntity, QAccount.account.permissions)
    }

    def rolePermissionsJoinCondition: CollectionJoinPathDescriptor[java.util.Set[Permission], Permission] = {
        CollectionJoinPathDescriptor(qEntity, QRole.role.permissions)
    }

    def permissionsCondition(permissions: Set[Permission]): BooleanExpression = {
        qEntity.name.in(permissions.map(_.getName).asJava)
    }
}
