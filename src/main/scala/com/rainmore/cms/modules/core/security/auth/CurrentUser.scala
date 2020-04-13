package com.rainmore.cms.modules.core.security.auth

import java.util

import com.rainmore.cms.domains.core.users.{Account, Permission, Role}
import com.rainmore.cms.modules.core.security.auth.CurrentUser.GrantedAuthorityImpl
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

object CurrentUser {
    object GrantedAuthorityImpl {
        def apply(role: Role): GrantedAuthorityImpl = GrantedAuthorityImpl(role.getName)
        def apply(permission: Permission): GrantedAuthorityImpl = GrantedAuthorityImpl(permission.getName)
    }

    case class GrantedAuthorityImpl(authority: String) extends GrantedAuthority {
        override def getAuthority: String = authority
    }
}

case class CurrentUser(account: Account, authorities: util.HashSet[_ <: GrantedAuthority]) extends UserDetails {

    override def isEnabled: Boolean = account.getStatus.isActive

    override def getPassword: String = account.getPassword

    override def isAccountNonExpired: Boolean = true

    override def isCredentialsNonExpired: Boolean = true

    override def getAuthorities: util.HashSet[_ <: GrantedAuthority] = authorities

    override def isAccountNonLocked: Boolean = true

    override def getUsername: String = account.getEmail

    def contains(authority: GrantedAuthorityImpl*): Boolean = {
        authority.toSet.exists(authorities.contains(_))
    }

}
