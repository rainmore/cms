package com.rainmore.cms.modules.core.jpa

import com.querydsl.core.types.{EntityPath, OrderSpecifier}
import com.querydsl.core.types.dsl.BooleanExpression
import com.rainmore.cms.domains.HasId


abstract class BaseSpecification[T <: HasId[ID], ID <: java.io.Serializable, Q <: EntityPath[T]](val qEntity: Q) {

    def idCondition(id: ID): BooleanExpression

    def archivedCondition(archived: Boolean): BooleanExpression

    def isActualCondition: BooleanExpression = archivedCondition(false)

    def isActualCondition(id: ID): BooleanExpression = idCondition(id).and(isActualCondition)
}
