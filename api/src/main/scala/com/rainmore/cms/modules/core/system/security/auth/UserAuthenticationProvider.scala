package com.rainmore.cms.modules.core.system.security.auth

import com.rainmore.cms.modules.core.users.services.AccountService
import org.apache.commons.lang3.StringUtils
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.context.support.MessageSourceAccessor
import org.springframework.context.{MessageSource, MessageSourceAware}
import org.springframework.security.authentication.{AuthenticationProvider, BadCredentialsException, UsernamePasswordAuthenticationToken}
import org.springframework.security.core.userdetails.{UserDetails, UsernameNotFoundException}
import org.springframework.security.core.{Authentication, AuthenticationException, SpringSecurityMessageSource}
import org.springframework.stereotype.Component

@Component
class UserAuthenticationProvider
(
    accountService: AccountService,
    userDetailsService: UserDetailsService
) extends AuthenticationProvider with MessageSourceAware {

    private val logger: Logger = LoggerFactory.getLogger(getClass)

    protected var messages: MessageSourceAccessor = SpringSecurityMessageSource.getAccessor

    override def setMessageSource(messageSource: MessageSource): Unit = {
        this.messages = new MessageSourceAccessor(messageSource)
    }

    override def supports(authentication: Class[_]): Boolean = classOf[UsernamePasswordAuthenticationToken].isAssignableFrom(authentication)

    @throws[AuthenticationException]
    override def authenticate(authentication: Authentication): Authentication = {
        val username: String = authentication.getName
        val userDetails = retrieveUser(username).asInstanceOf[CurrentUser]

        credentialChecks(userDetails, authentication.asInstanceOf[UsernamePasswordAuthenticationToken])

        new UsernamePasswordAuthenticationToken(userDetails, authentication.getCredentials, userDetails.getAuthorities)
    }

    @throws[AuthenticationException]
    def credentialChecks(userDetails: UserDetails, authentication: UsernamePasswordAuthenticationToken): Unit = {
        if (authentication.getCredentials == null) {
            logger.debug("Authentication failed: no credentials provided")
            throw new BadCredentialsException(messages.getMessage("auth.error.password.invalid", "Bad credentials"))
        }
        Option(StringUtils.trimToNull(authentication.getCredentials.toString)) match {
            case None => throw new BadCredentialsException(messages.getMessage("auth.error.password.invalid", "Bad credentials"))
            case Some(password) =>
                if (!accountService.matchPassword(password, userDetails.getPassword)) {
                    throw new BadCredentialsException(messages.getMessage("auth.error.password.invalid", "Bad credentials"))
                }
        }
    }

    @throws[AuthenticationException]
    def retrieveUser(username: String): UserDetails = {
        try {
            userDetailsService.loadUserByUsername(username)
        }
        catch {
            case noFound: UsernameNotFoundException => throw new UsernameNotFoundException(messages.getMessage(noFound.getMessage, "User not found"))
        }
    }
}
