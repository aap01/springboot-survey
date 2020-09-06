package com.aap.springboot_skeleton

import com.aap.springboot_skeleton.repository.AuthRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.repository.Repository

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = [
    AuthRepository::class
])
class SpringbootSkeletonApplication

fun main(args: Array<String>) {
    runApplication<SpringbootSkeletonApplication>(*args)
}
