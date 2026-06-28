package de.thm.mni.knowledgehub.security

import de.thm.mni.knowledgehub.student.StudentRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * Loads students from the database for Spring Security authentication.
 */
@Service
class CustomStudentDetailsService(
    private val studentRepository: StudentRepository
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {

        val student = studentRepository.findByEmail(username)
            ?: throw UsernameNotFoundException(
                "Student with email '$username' was not found."
            )

        return User.builder()
            .username(student.email)
            .password(student.password)
            .authorities(
                listOf(SimpleGrantedAuthority("ROLE_STUDENT"))
            )
            .build()
    }
}