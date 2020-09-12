package com.aap.springboot_skeleton.repository

import com.aap.springboot_skeleton.model.db.DBAuth
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AuthRepository : JpaRepository<DBAuth, Long> {
    fun findByUsername(username: String): Optional<DBAuth>
}