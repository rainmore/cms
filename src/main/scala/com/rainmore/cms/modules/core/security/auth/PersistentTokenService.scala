package com.rainmore.cms.modules.core.security.auth

import java.util.Date

import com.rainmore.cms.domains.core.users.{Account, LoginToken}
import com.rainmore.cms.modules.core.users.services.AccountService
import com.rainmore.cms.utils.TimeUtils
import javax.inject.Inject
import javax.persistence.EntityNotFoundException
import javax.transaction.Transactional
import org.springframework.security.web.authentication.rememberme.{PersistentRememberMeToken, PersistentTokenRepository}
import org.springframework.stereotype.Service

import scala.compat.java8.OptionConverters._
import scala.jdk.CollectionConverters._

@Service
class PersistentTokenService @Inject()
(
    accountService: AccountService,
    loginTokenRepository: LoginTokenRepository
) extends PersistentTokenRepository {

    def fineOne(id: String): Option[LoginToken] = loginTokenRepository.findById(id).asScala

    def fine(account: Account): Seq[LoginToken] = loginTokenRepository
        .findAll(LoginTokenSpecification.accountCondition(account)).asScala.toSeq

    @Transactional
    def save(loginToken: LoginToken): LoginToken = loginTokenRepository.save(loginToken)

    def exists(id: String): Boolean = loginTokenRepository.existsById(id)

    @Transactional
    def delete(loginToken: LoginToken): Unit = loginTokenRepository.delete(loginToken)

    @Transactional
    def deleteByUsername(username: String): Unit = {
        val tokens = loginTokenRepository.findAll(LoginTokenSpecification.usernameCondition(username))
        loginTokenRepository.deleteAll(tokens)
    }

    private def buildPersistentRememberMeToken(loginToken: LoginToken) = {
        new PersistentRememberMeToken(
            loginToken.getAccount.getEmail,
            loginToken.getId,
            loginToken.getToken,
            TimeUtils.asDate(loginToken.getLastUsedAt))
    }

    override def getTokenForSeries(id: String): PersistentRememberMeToken = {
        fineOne(id).map(buildPersistentRememberMeToken).orNull
    }

    @Transactional
    override def updateToken(id: String, tokenValue: String, lastUsed: Date): Unit = {
        fineOne(id).foreach(token => {
            token.setToken(tokenValue)
            token.setLastUsedAt(TimeUtils.asLocalDateTime(lastUsed))
            save(token)
        })
    }

    @Transactional
    override def createNewToken(persistentRememberMeToken: PersistentRememberMeToken): Unit = {
        val account: Account = accountService.findOne(persistentRememberMeToken.getUsername)
            .filter(_.getStatus.isActive)
            .getOrElse(throw new EntityNotFoundException())
        val token = new LoginToken(
            persistentRememberMeToken.getSeries,
            persistentRememberMeToken.getTokenValue,
            account,
            TimeUtils.now
        )
        save(token)
    }

    @Transactional
    override def removeUserTokens(username: String): Unit = {
        deleteByUsername(username)
    }
}
