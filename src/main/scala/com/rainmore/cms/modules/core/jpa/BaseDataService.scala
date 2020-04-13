package com.rainmore.cms.modules.core.jpa

import com.querydsl.core.types.OrderSpecifier
import com.querydsl.core.types.dsl.{BooleanExpression, EntityPathBase}
import com.rainmore.cms.domains.HasId
import com.rainmore.cms.domains.core.users.Account
import javax.transaction.Transactional
import org.springframework.data.domain.{Page, Pageable, Sort}
import org.springframework.data.querydsl.QSort

import scala.jdk.CollectionConverters._
import scala.jdk.OptionConverters._

trait BaseDataService[T <: HasId[ID], ID <: java.io.Serializable, Q <: EntityPathBase[T], R <: BaseRepository[T, ID, Q], S <: BaseSpecification[T, ID]] {

    protected val getRepository: R

    protected val getSpecification: S

    def exists(id: ID): Boolean = getRepository.existsById(id)

    def existsActual(id: ID): Boolean = getRepository.exists(getSpecification.isActualCondition(id))

    protected def findOne(id: ID): Option[T] = getRepository.findById(id).toScala

    protected def findOne(criteria: BooleanExpression): Option[T] = getRepository.findOne(criteria).toScala

    protected def findByCriteria(criteria: BooleanExpression, sort: Seq[OrderSpecifier[_]]): Seq[T] = getRepository.findAll(criteria, sort: _*).asScala.toVector

    protected def findPageByCriteria(criteria: BooleanExpression, pageable: Pageable): Page[T] = getRepository.findAll(criteria, pageable)

    def findOneActual(id: ID): Option[T] = findOne(getSpecification.isActualCondition(id))

    def findPage(pageable: Pageable): Page[T] = findPageByCriteria(getSpecification.isActualCondition, pageable)

    @Transactional
    def archive(entity: T, updatedBy: Account): Unit

    @Transactional
    def save(entity: T, updatedBy: Account): Unit

}
