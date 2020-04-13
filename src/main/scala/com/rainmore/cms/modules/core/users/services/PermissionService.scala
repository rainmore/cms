package com.rainmore.cms.modules.core.users.services

import java.lang.{Long => JLong}

import com.rainmore.cms.domains.core.users.{Account, Permission, QPermission, Role}
import com.rainmore.cms.modules.core.jpa.BaseDataService
import javax.inject.Inject
import javax.transaction.Transactional
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.stereotype.Service

@Service("core.users.PermissionService")
class PermissionService @Inject()
(
    permissionRepository: PermissionRepository
) extends BaseDataService[Permission, JLong, QPermission, PermissionRepository, PermissionSpecification] {

    override protected val getRepository: PermissionRepository = permissionRepository
    override protected val getSpecification: PermissionSpecification = new PermissionSpecification

    def exists(name: String, id: Option[JLong]): Boolean = {
        val criteria = getSpecification.nameCondition(name, id)
        getRepository.exists(criteria)
    }

    def find(roles: Set[Role]): Set[Permission] = getRepository.findBy(roles).toSet

    def find(account: Account): Set[Permission] = getRepository.findBy(account).toSet

    override def findPage(pageable: Pageable): Page[Permission] = {
        val criteria = getSpecification.isActualCondition.and(getSpecification.parentCondition(true))
        findPageByCriteria(criteria, pageable)
    }

    def find: Seq[Permission] = {
        val criteria = getSpecification.isActualCondition.and(getSpecification.parentCondition(true))
        findByCriteria(criteria, getSpecification.defaultSort.toSeq)
    }

    @Transactional
    def save(permission: Permission, updatedBy: Account): Unit = {
        permission.update(updatedBy)
        getRepository.save(permission)
    }

    @Transactional
    override def archive(entity: Permission, updatedBy: Account): Unit = {
        entity.archive(updatedBy)
        save(entity, updatedBy)
    }
}
