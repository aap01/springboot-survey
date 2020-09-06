package com.aap.springboot_skeleton.repository

import com.aap.springboot_skeleton.model.DBQuestion
import org.springframework.data.jpa.repository.JpaRepository

interface QuestionRepository: JpaRepository<DBQuestion, Long> {

}