package de.thm.mni.knowledgehub.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder

@Configuration
class AuthenticationConfig(

    private val studentDetailsService: CustomStudentDetailsService,
    private val passwordEncoder: PasswordEncoder

) {

    @Bean
    fun authenticationProvider(): AuthenticationProvider {

        val provider = DaoAuthenticationProvider(studentDetailsService)

        provider.setPasswordEncoder(passwordEncoder)

        return provider
    }

    @Bean
    fun authenticationManager(
        configuration: AuthenticationConfiguration
    ): AuthenticationManager =
        configuration.authenticationManager
}