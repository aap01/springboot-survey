package com.aap.springboot_skeleton.repository

import com.aap.springboot_skeleton.model.db.DBQuestion
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository: JpaRepository<DBQuestion, Long> {

}