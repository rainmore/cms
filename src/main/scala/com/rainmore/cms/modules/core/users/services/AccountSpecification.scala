package com.rainmore.cms.modules.core.users.services

import java.lang.{Long => JLong}
import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.JPAExpressions
import com.rainmore.cms.domains.users.{Account, QAccount, QRole, Role}
import com.rainmore.cms.modules.core.jpa.BaseSpecification

import scala.jdk.CollectionConverters._


class AccountSpecification extends BaseSpecification[Account, JLong, QAccount](QAccount.account) {

    def defaultSort: Array[OrderSpecifier[_ <: Comparable[_]]] = Array(qEntity.firstName.asc())

    override def idCondition(id: JLong): BooleanExpression = qEntity.id.eq(id)

    override def archivedCondition(archived: Boolean): BooleanExpression = {
        if (archived) qEntity.archivedAt.isNotNull
        else qEntity.archivedAt.isNull
    }

    def accountCondition(account: Account): BooleanExpression = qEntity.eq(account)

    def emailCondition(email: String, id: Option[Long]): BooleanExpression = {
        var conditions = qEntity.email.eq(email)

        id.foreach(id => conditions = conditions.and(notIdCondition(id)))

        conditions
    }

    def emailCondition(email: String): BooleanExpression = emailCondition(email, None)

    def notIdCondition(id: Long): BooleanExpression = {
        qEntity.id.ne(id)
    }

    def rolesCondition(roles: Set[Role]): BooleanExpression = {
        val qAccount = qEntity
        val qRole = QRole.role
        val subQuery = JPAExpressions.selectFrom(qAccount)
            .join(qEntity.roles, qRole)
            .where(qRole.in(roles.asJava))
            .select(qEntity.id)
        qEntity.id.in(subQuery)
    }

}
