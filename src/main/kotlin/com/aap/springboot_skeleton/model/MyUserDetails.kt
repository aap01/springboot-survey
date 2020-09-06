package com.aap.springboot_skeleton.model

import com.aap.springboot_skeleton.util.Constants.Companion.ROLE
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class MyUserDetails(private val auth: DBAuth) : UserDetails {

    override fun getAuthorities(): List<GrantedAuthority> {
        return auth.roles.split(",").map { SimpleGrantedAuthority(ROLE + it) }
    }

    override fun getPassword(): String {
        return auth.password
    }

    override fun getUsername(): String {
        return auth.username
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }

}
