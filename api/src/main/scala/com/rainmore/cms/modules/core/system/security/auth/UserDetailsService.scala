package com.rainmore.cms.modules.core.system.security.auth

import java.lang.{String => JString}
import java.time.Duration
import java.util
import com.rainmore.cms.domains.users.Account
import com.rainmore.cms.domains.users.Account
import com.rainmore.cms.modules.core.system.SystemPropertyService
import com.rainmore.cms.modules.core.system.security.auth.CurrentUser.GrantedAuthorityImpl
import com.rainmore.cms.modules.core.users.services.{AccountService, PermissionService, RoleService}

import javax.inject.Inject
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.{UserDetails, UsernameNotFoundException}
import org.springframework.stereotype.Component

import scala.jdk.CollectionConverters._

@Component
class UserDetailsService @Inject()
(
    accountService: AccountService,
    roleService: RoleService,
    permissionService: PermissionService,
    systemPropertyService: SystemPropertyService
) extends org.springframework.security.core.userdetails.UserDetailsService {

    override def loadUserByUsername(username: JString): UserDetails = {
        accountService.findOne(username).filter(_.getStatus.isActive) match {
            case None => throw new UsernameNotFoundException("auth.error.username.notFound")
            case Some(account) =>
                accountService.updateLastLoginAt(account)
                load(account)
        }
    }

    private def load(account: Account): CurrentUser = {
        val roles = roleService.findBy(account).toSet
        account.setRoles(roles.asJava)

        val permissions = {
            val accountPermissions = permissionService.find(account)
            account.setPermissions(accountPermissions.asJava)

            if (accountPermissions.isEmpty) permissionService.find(roles)
            else accountPermissions
        }


        val authorities: util.HashSet[_ <: GrantedAuthority] = {
            val set = roles.map(GrantedAuthorityImpl(_)) ++ permissions.map(GrantedAuthorityImpl(_))
            new util.HashSet[GrantedAuthority](set.asJava)
        }

        CurrentUser(account, authorities)
    }

    def refreshAuthentication(account: Account): Unit = {
        val auth = SecurityContextHolder.getContext.getAuthentication

        val currentUser = load(account)

        val authentication = new UsernamePasswordAuthenticationToken(
            currentUser,
            auth.getCredentials,
            currentUser.getAuthorities)

        SecurityContextHolder.getContext.setAuthentication(authentication)
    }

    def getRememberMeDurationInSeconds: Int = {
        systemPropertyService.getData[Int]("rainmore.cms.core.system.security.rememberMe.duration", classOf[Int])
    }
}
