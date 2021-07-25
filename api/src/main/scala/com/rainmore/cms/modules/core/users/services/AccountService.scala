package com.rainmore.cms.modules.core.users.services

import com.rainmore.cms.domains.users.{Account, QAccount, Role}

import java.lang.{Long => JLong}
import java.time.LocalDateTime
import com.rainmore.cms.modules.core.jpa.BaseDataService

import javax.inject.Inject
import javax.transaction.Transactional
import org.springframework.data.domain.{Page, Pageable}
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

object AccountService {

    private val SystemAccountId = 1L

}

@Service("core.users.AccountService")
class AccountService @Inject()
(
    roleService: RoleService,
    accountRepository: AccountRepository
) extends BaseDataService[Account, JLong, QAccount, AccountRepository, AccountSpecification] {

    override protected val getRepository = accountRepository

    override protected val getSpecification = new AccountSpecification

    private val passwordEncoder = new BCryptPasswordEncoder

    def matchPassword(rawPassword: String, password: String): Boolean = {
        passwordEncoder.matches(rawPassword.toString, password)
    }

    def checkDuplicatedEmail(email: String, id: Long): Boolean = {
        val criteria = getSpecification.emailCondition(email, Some(id))
        getRepository.exists(criteria)
    }

    def exists(email: String): Boolean = {
        val criteria = getSpecification.isActualCondition.and(getSpecification.emailCondition(email, None))
        getRepository.exists(criteria)
    }

    def encodePassword(password: String): String = passwordEncoder.encode(password)


    def findSystemAccount(): Account = accountRepository.findById(AccountService.SystemAccountId).get()

    def findOne(email: String): Option[Account] = {
        val criteria = getSpecification.isActualCondition.and(getSpecification.emailCondition(email))
        findOne(criteria)
    }

    @Transactional
    def updateLastLoginAt(account: Account): Unit = {
        account.setLastLoginAt(LocalDateTime.now.withNano(0))
        accountRepository.save(account)
    }

    @Transactional
    override def save(account: Account, updatedBy: Account): Unit = {
        account.updateBy(updatedBy)
        accountRepository.save(account)
    }

    @Transactional
    override def archive(account: Account, archivedBy: Account): Unit = {
        account.archive(archivedBy)
        accountRepository.save(account)
    }

    @Transactional
    def unarchive(account: Account, updatedBy: Account): Unit = {
        account.setArchivedBy(null)
        account.setArchivedAt(null)
        save(account, updatedBy)
    }

    def findActualRegulars(pageable: Pageable): Page[Account] = {
        val roles = roleService.findRegulars.toSet
        val criteria = getSpecification.isActualCondition.and(getSpecification.rolesCondition(roles))
        findPageByCriteria(criteria, pageable)
    }

    def findActiveBy(roles: Set[Role]): Iterable[Account] = {
        val roles = roleService.findRegulars.toSet
        val criteria = getSpecification.isActualCondition.and(getSpecification.rolesCondition(roles))
        findByCriteria(criteria, getSpecification.defaultSort.toSeq)
    }

    def isSystemAccount(account: Account): Boolean = account.getId == AccountService.SystemAccountId

}