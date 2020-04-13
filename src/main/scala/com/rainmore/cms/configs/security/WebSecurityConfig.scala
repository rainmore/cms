package com.rainmore.cms.configs.security


import com.rainmore.cms.modules.core.system.security.auth.{PersistentTokenService, UserAuthenticationProvider, UserDetailsService}
import javax.inject.Inject
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.{HttpSecurity, WebSecurity}
import org.springframework.security.config.annotation.web.configuration.{EnableWebSecurity, WebSecurityConfigurerAdapter}

@EnableWebSecurity //Please do NOT use `EnableWebMvcSecurity` as `WebSecurityConfiguration` is customized configuration
@Configuration
class WebSecurityConfig @Inject()
(
    persistentTokenService: PersistentTokenService,
    userDetailsService: UserDetailsService,
    userAuthenticationProvider: UserAuthenticationProvider
) extends WebSecurityConfigurerAdapter {

    @throws[Exception]
    override def configure(auth: AuthenticationManagerBuilder): Unit = {
        auth.authenticationProvider(userAuthenticationProvider)
    }

    @throws[Exception]
    override def configure(web: WebSecurity): Unit = {
        // static files
        val ignored = Array(
            "/400",
            "/401",
            "/404",
            "/500",
            "/webjars/**",
            "/images/**",
            "/jqwidgets/**",
            "/lib/**",
            "/js/**",
            "/css/**"
        )
        web.ignoring.antMatchers(ignored: _*)
    }


    @throws[Exception]
    override protected def configure(http: HttpSecurity): Unit = {
        http.authorizeRequests()
            .antMatchers("/auth/forget-password", "/auth/reset-password/**").permitAll()
            .and()
            .authorizeRequests().anyRequest().authenticated()
            .and()
            .formLogin
            .defaultSuccessUrl("/dashboard") // `defaultSuccessUrl` is not available but please do not touch this part any more
            .loginPage("/auth/login")
            .permitAll
            .and()
            .logout
            .invalidateHttpSession(true)
            .clearAuthentication(true)
            .deleteCookies("JSESSIONID", "remember-me")
            .logoutSuccessUrl("/auth/login?logout")
            .permitAll
            .and()
            .rememberMe
            .tokenValiditySeconds(userDetailsService.getRememberMeDurationInSeconds)
            .tokenRepository(persistentTokenService)
            .userDetailsService(userDetailsService)
            .and()
            .exceptionHandling().accessDeniedPage("/403")
            .and()
            .csrf().disable()
        ;
    }

}