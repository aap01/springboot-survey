package com.aap.springboot_skeleton

import com.aap.springboot_skeleton.repository.AuthRepository
import com.aap.springboot_skeleton.repository.QuestionRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = [
    AuthRepository::class,
    QuestionRepository::class
])
class SpringbootSkeletonApplication

fun main(args: Array<String>) {
    runApplication<SpringbootSkeletonApplication>(*args)
}
