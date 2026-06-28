package de.thm.mni.knowledgehub.auth.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive

data class RegisterRequest(

    @field:Email
    val email: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val firstName: String,

    @field:NotBlank
    val lastName: String,

    @field:NotBlank
    val major: String,

    @field:NotBlank
    val university: String,

    @field:Positive
    val semester: Int
)