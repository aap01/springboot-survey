package com.aap.springboot_skeleton.model

import com.aap.springboot_skeleton.model.validator.ValuesAllowed
import com.aap.springboot_skeleton.util.Resources
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "question")
data class DBQuestion(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    @field:NotBlank(message = Resources.required)
    val question: String,
    @field:NotBlank(message = Resources.required)
    @field:ValuesAllowed(values = [TEXT, NUMBER, MULTI_CHOICE, DROPDOWN, CHECKBOX])
    val type: String,
    val options: String,
    val required: Boolean = true
) {
    companion object {
        private const val TEXT = "text"
        private const val NUMBER = "number"
        private const val MULTI_CHOICE = "multiple choice"
        private const val DROPDOWN = "dropdown"
        private const val CHECKBOX = "checkbox"
    }
}