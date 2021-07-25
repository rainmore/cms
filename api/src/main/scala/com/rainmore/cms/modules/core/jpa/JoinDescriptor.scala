package com.rainmore.cms.modules.core.jpa

import com.querydsl.core.JoinType
import com.querydsl.core.types.{CollectionExpression, EntityPath}

trait JoinDescriptor[T] {

    def joinType: JoinType

    def fetchJoin: Boolean
}

case class EntityJoinDescriptor[T]
(
    path: EntityPath[T],
    override val joinType: JoinType,
    override val fetchJoin: Boolean
) extends JoinDescriptor[T]

case class CollectionJoinDescriptor[C <: java.util.Collection[T], T]
(
    collectionPath: CollectionExpression[C, T],
    override val joinType: JoinType = JoinType.INNERJOIN,
    override val fetchJoin: Boolean = false
) extends JoinDescriptor[T]

case class CollectionJoinPathDescriptor[C <: java.util.Collection[T], T]
(
    path: EntityPath[T],
    collectionPath: CollectionExpression[C, T],
    override val joinType: JoinType = JoinType.INNERJOIN,
    override val fetchJoin: Boolean = false
) extends JoinDescriptor[T]