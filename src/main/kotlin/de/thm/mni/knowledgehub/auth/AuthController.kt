package de.thm.mni.knowledgehub.auth

import de.thm.mni.knowledgehub.auth.dto.LoginRequest
import de.thm.mni.knowledgehub.auth.dto.LoginResponse
import de.thm.mni.knowledgehub.auth.dto.RegisterRequest
import de.thm.mni.knowledgehub.auth.dto.RegisterResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    fun register(
        @Valid
        @RequestBody
        request: RegisterRequest
    ): RegisterResponse =
        authService.register(request)

    @PostMapping("/login")
    fun login(
        @Valid
        @RequestBody
        request: LoginRequest
    ): LoginResponse =
        authService.login(request)
}