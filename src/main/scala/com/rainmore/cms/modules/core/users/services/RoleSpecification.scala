package com.rainmore.cms.modules.core.users.services


import java.lang.{Long => JLong}
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.rainmore.cms.domains.users.{QAccount, QRole, Role}
import com.rainmore.cms.domains.users.{QAccount, QRole, Role}
import com.rainmore.cms.modules.core.jpa.{BaseSpecification, CollectionJoinPathDescriptor}


class RoleSpecification extends BaseSpecification[Role, JLong, QRole](QRole.role) {

    def defaultSort: Array[OrderSpecifier[_ <: Comparable[_]]] = Array(QRole.role.name.asc())

    override def idCondition(id: JLong): BooleanExpression = QRole.role.id.eq(id)

    override def archivedCondition(archived: Boolean): BooleanExpression = {
        if (archived) QRole.role.archivedAt.isNotNull
        else QRole.role.archivedAt.isNull
    }

    def roleCondition(role: Role): BooleanExpression = QRole.role.eq(role)

    def roleCondition(roles: Set[Role]): BooleanExpression = QRole.role.in(roles.toSeq: _*)

    def nameCondition(name: String, id: Option[JLong]): BooleanExpression = {
        val role = QRole.role
        id match {
            case Some(roleId) => role.name.eq(name).and(role.id.ne(roleId))
            case None => role.name.eq(name)
        }
    }

    def isAlmightyCondition(isAlmighty: Boolean): BooleanExpression = {
        require(Option(isAlmighty).isDefined)
        if (isAlmighty) QRole.role.almighty.eq(true)
        else QRole.role.almighty.eq(false)
    }

    def isRegularCondition: BooleanExpression = isAlmightyCondition(false)

    def accountRolesJoinCondition: CollectionJoinPathDescriptor[java.util.Set[Role], Role] = {
        CollectionJoinPathDescriptor(QRole.role, QAccount.account.roles)
    }

}
