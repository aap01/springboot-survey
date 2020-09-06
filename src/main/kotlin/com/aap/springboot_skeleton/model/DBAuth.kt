package com.aap.springboot_skeleton.model

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "auth")
data class DBAuth(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @Column(unique = true)
    @NotBlank
    val username: String,
    @NotBlank
    val password: String,
    val roles: String = ROLES.USER.toString()
) {

}