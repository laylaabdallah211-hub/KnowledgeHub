package de.thm.mni.knowledgehub.auth.dto

import java.util.UUID

data class RegisterResponse(
    val id: UUID,
    val email: String
)