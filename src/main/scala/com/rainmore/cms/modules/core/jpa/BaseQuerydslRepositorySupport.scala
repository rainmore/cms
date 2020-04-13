package com.rainmore.cms.modules.core.jpa

import com.querydsl.core.JoinType
import com.querydsl.core.types.dsl.{BooleanExpression, EntityPathBase}
import com.querydsl.core.types.{EntityPath, OrderSpecifier}
import com.querydsl.jpa.JPQLQuery
import com.rainmore.cms.domains.HasId

import scala.jdk.CollectionConverters._

trait BaseQuerydslRepositorySupport[T <: HasId[ID], ID <: java.io.Serializable, Q <: EntityPathBase[T]] {

    def findOne(predicate: BooleanExpression,
                joinDescriptors: Set[JoinDescriptor[_]]): Option[T]

    def findOne[E <: HasId[ID]](anyEntityPath: EntityPath[E], predicate: BooleanExpression,
                            joinDescriptors: Set[JoinDescriptor[_]]): Option[T]

    def findAll(predicate: BooleanExpression,
                joinDescriptors: Set[JoinDescriptor[_]],
                orders: Seq[OrderSpecifier[_]],
                limit: Option[Long],
                offset: Option[Long]): Seq[T]

    def findAny[E <: HasId[ID]](anyEntityPath: EntityPath[E],
                            predicate: BooleanExpression,
                            joinDescriptors: Set[JoinDescriptor[_]],
                            orders: Seq[OrderSpecifier[_]],
                            limit: Option[Long],
                            offset: Option[Long]): Seq[T]

}

abstract class BaseQuerydslRepositorySupportImpl[T <: HasId[ID], ID <: java.io.Serializable, Q <: EntityPathBase[T]]
(
    clazz: Class[T],
    qEntity: Q
) extends org.springframework.data.jpa.repository.support.QuerydslRepositorySupport(clazz)
        with BaseQuerydslRepositorySupport[T, ID, Q] {

    override def findOne(predicate: BooleanExpression,
                         joinDescriptors: Set[JoinDescriptor[_]]): Option[T] = {
        findOne(qEntity, predicate, joinDescriptors)
    }

    override def findOne[E <: HasId[ID]](anyEntityPath: EntityPath[E],
                                     predicate: BooleanExpression,
                                     joinDescriptors: Set[JoinDescriptor[_]]): Option[T] = {
        val query = buildQuery(anyEntityPath, predicate, joinDescriptors, Seq(), None, None)
        Option(query.fetchOne())
    }

    override def findAll(predicate: BooleanExpression,
                         joinDescriptors: Set[JoinDescriptor[_]],
                         orders: Seq[OrderSpecifier[_]],
                         limit: Option[Long],
                         offset: Option[Long]): Seq[T] = {
        findAny(qEntity, predicate, joinDescriptors, orders, limit, offset)
    }

    override def findAny[E <: HasId[ID]](anyEntityPath: EntityPath[E],
                                     predicate: BooleanExpression,
                                     joinDescriptors: Set[JoinDescriptor[_]],
                                     orders: Seq[OrderSpecifier[_]],
                                     limit: Option[Long],
                                     offset: Option[Long]): Seq[T] = {

        val query = buildQuery(anyEntityPath, predicate, joinDescriptors, orders, limit, offset)

        query.fetch().asScala.toSeq
    }

    private def buildQuery[E <: HasId[ID], O <: Comparable[_]](anyEntityPath: EntityPath[E],
                                                           predicate: BooleanExpression,
                                                           joinDescriptors: Set[JoinDescriptor[_]],
                                                           orders: Seq[OrderSpecifier[_]],
                                                           limit: Option[Long],
                                                           offset: Option[Long]): JPQLQuery[T] = {
        var query: JPQLQuery[T] = baseQuery(anyEntityPath, predicate, joinDescriptors).select(qEntity)

        if (orders.nonEmpty) query = query.orderBy(orders:_*)

        limit.foreach(limit => query = query.limit(limit))

        offset.foreach(offset => query = query.limit(offset))

        query
    }

    private def baseQuery[E <: HasId[ID], O <: Comparable[_]](anyEntityPath: EntityPath[E],
                                                          predicate: BooleanExpression,
                                                          joinDescriptors: Set[JoinDescriptor[_]]): JPQLQuery[E] = {
        var query: JPQLQuery[E] = from(anyEntityPath)
        joinDescriptors.foreach(joinDescriptor => {
            query = buildAnyJoin(joinDescriptor, query)
        })

        if (joinDescriptors.nonEmpty) {
            query = query.distinct
        }

        query = query.where(predicate)

        query
    }

    private def buildJoin[E <: HasId[ID]](joinDescriptor: JoinDescriptor[_],
                                      query: JPQLQuery[E]): JPQLQuery[E] = {
        buildAnyJoin(joinDescriptor, query)
    }

    private def buildAnyJoin[E <: HasId[ID], S](joinDescriptor: JoinDescriptor[S],
                                            query: JPQLQuery[E]): JPQLQuery[E] = {
        joinDescriptor.joinType match {
            case JoinType.INNERJOIN =>
                joinDescriptor match {
                    case EntityJoinDescriptor(path, _, _) => query.innerJoin(path)
                    case CollectionJoinDescriptor(collectionPath, _, _) => query.innerJoin(collectionPath)
                    case CollectionJoinPathDescriptor(path, collectionPath, _, _) =>
                        query.innerJoin(collectionPath, path).select(path)
                }
            case JoinType.LEFTJOIN =>
                joinDescriptor match {
                    case EntityJoinDescriptor(path, _, _) => query.leftJoin(path)
                    case CollectionJoinDescriptor(collectionPath, _, _) => query.leftJoin(collectionPath)
                    case CollectionJoinPathDescriptor(path, collectionPath, _, _) =>
                        query.leftJoin(collectionPath, path).select(path)
                }
            case _ => throw new UnsupportedOperationException("Only left join and inner join are supported")
        }
        if (joinDescriptor.fetchJoin) query.fetchJoin
        query
    }

}
