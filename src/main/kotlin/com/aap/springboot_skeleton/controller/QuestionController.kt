package com.aap.springboot_skeleton.controller

import com.aap.springboot_skeleton.model.DBQuestion
import com.aap.springboot_skeleton.model.response.GenericResponse
import com.aap.springboot_skeleton.model.response.SuccessResponse
import com.aap.springboot_skeleton.service.QuestionService
import com.aap.springboot_skeleton.util.Resources
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@Validated
class QuestionController {

    @Autowired
    private lateinit var questionService: QuestionService

    @PostMapping("/questions")
    fun add(@Valid @RequestBody question: DBQuestion): ResponseEntity<GenericResponse> {
        return ResponseEntity.ok(SuccessResponse(message = Resources.questionAdded, body = questionService.add(question)))
    }

    @GetMapping("/survey")
    fun getQuestions(): ResponseEntity<GenericResponse> {
        return ResponseEntity.ok(SuccessResponse(message = Resources.questionsFetched, body = questionService.getAll()))
    }
}