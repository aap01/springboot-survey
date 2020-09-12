package com.aap.springboot_skeleton.model.db

import com.aap.springboot_skeleton.model.ROLES
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
    val roles: String = ROLES.USER.toString(),
    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinColumn(name = "info_id", referencedColumnName = "id")
    val userPersonalInfo: DbUserPersonalInfo
)