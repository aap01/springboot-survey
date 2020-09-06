package com.aap.springboot_skeleton.adapter

import com.aap.springboot_skeleton.controller.AuthController
import com.aap.springboot_skeleton.controller.PingController
import com.aap.springboot_skeleton.service.MyUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter


@EnableWebSecurity
class SecurityConfigurerAdapter : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var myUserDetailsService: MyUserDetailsService

    @Autowired
    private lateinit var requestFilter: OncePerRequestFilter

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(myUserDetailsService)
    }

    override fun configure(http: HttpSecurity?) {
        http?.csrf()
            ?.disable()
            ?.authorizeRequests()
            ?.antMatchers(AuthController.LOGIN, AuthController.SIGN_UP, PingController.PING)?.permitAll()
            ?.anyRequest()?.authenticated()
            ?.and()
            ?.sessionManagement()?.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http?.addFilterBefore(requestFilter, UsernamePasswordAuthenticationFilter::class.java)
    }


    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

}