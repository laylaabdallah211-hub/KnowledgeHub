package de.thm.mni.knowledgehub.auth

import de.thm.mni.knowledgehub.auth.dto.LoginRequest
import de.thm.mni.knowledgehub.auth.dto.LoginResponse
import de.thm.mni.knowledgehub.auth.dto.RegisterRequest
import de.thm.mni.knowledgehub.auth.dto.RegisterResponse
import de.thm.mni.knowledgehub.security.JwtService
import de.thm.mni.knowledgehub.student.Student
import de.thm.mni.knowledgehub.student.StudentRepository
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AuthService(
    private val studentRepository: StudentRepository,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtService: JwtService
) {

    fun register(request: RegisterRequest): RegisterResponse {

        if (studentRepository.findByEmail(request.email) != null) {
            throw IllegalArgumentException("A student with this email already exists.")
        }

        val rawPassword: String = request.password
        val encodedPassword = requireNotNull(passwordEncoder.encode(rawPassword)) {
            "PasswordEncoder returned null"
        }

        val student = Student(
            id = UUID.randomUUID(),
            email = request.email,
            firstName = request.firstName,
            lastName = request.lastName,


            password = encodedPassword,

            major = request.major,
            university = request.university,
            semester = request.semester
        )

        val savedStudent = studentRepository.save(student)

        return RegisterResponse(
            id = savedStudent.id,
            email = savedStudent.email
        )
    }

    fun login(request: LoginRequest): LoginResponse {

        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.email,
                request.password
            )
        )

        val userDetails = userDetailsService.loadUserByUsername(request.email)

        val token = jwtService.generateToken(userDetails)

        return LoginResponse(token)
    }
}