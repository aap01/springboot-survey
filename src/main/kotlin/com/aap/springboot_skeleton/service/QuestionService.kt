package com.aap.springboot_skeleton.service

import com.aap.springboot_skeleton.model.DBQuestion
import com.aap.springboot_skeleton.repository.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class QuestionService {

    @Autowired
    private lateinit var questionRepository: QuestionRepository

    @Transactional
    fun add(dbQuestion: DBQuestion): DBQuestion {
        return questionRepository.save(dbQuestion)
    }

    @Transactional
    fun getAll(): List<DBQuestion> {
        return  questionRepository.findAll()
    }
}