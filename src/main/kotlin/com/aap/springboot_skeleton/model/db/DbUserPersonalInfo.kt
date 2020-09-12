package com.aap.springboot_skeleton.model.db

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "user_personal_info")
data class DbUserPersonalInfo (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val imageUrl: String? = null
)