package com.rainmore.cms.modules.core.users.services

import java.lang.{Long => JLong}

import com.rainmore.cms.domains.core.users.{Account, QRole, Role}
import com.rainmore.cms.modules.core.jpa.BaseDataService
import javax.inject.Inject
import javax.transaction.Transactional
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.stereotype.Service

import scala.compat.java8.OptionConverters._

@Service("core.users.PermissionService")
class RoleService @Inject()
(
    roleRepository: RoleRepository
) extends BaseDataService[Role, JLong, QRole, RoleRepository, RoleSpecification] {

    override protected val getRepository = roleRepository
    override protected val getSpecification = new RoleSpecification

    def exists(name: String, id: Option[JLong]): Boolean = {
        val criteria = getSpecification.nameCondition(name, id)
        roleRepository.exists(criteria)
    }

    def findPageRegulars(pageable: Pageable): Page[Role] = {
        val criteria = getSpecification.isActualCondition.and(getSpecification.isRegularCondition)
        findPageByCriteria(criteria, pageable)
    }

    def findRegulars: Seq[Role] = {
        val criteria = getSpecification.isActualCondition.and(getSpecification.isRegularCondition)
        findByCriteria(criteria, getSpecification.defaultSort.toSeq)
    }

    def findBy(account: Account): Seq[Role] = {
        getRepository.findBy(account)
    }

    @Transactional
    override def archive(role: Role, updatedBy: Account): Unit = {
        role.archive(updatedBy)
        save(role, updatedBy)
    }

    @Transactional
    override def save(role: Role, updatedBy: Account): Unit = {
        role.update(updatedBy)
        roleRepository.save(role)
    }

}
