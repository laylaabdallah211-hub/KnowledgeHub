package de.thm.mni.knowledgehub.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import javax.crypto.SecretKey
import java.util.Date

@Service
class JwtService(

    @Value("\${jwt.secret}")
    private val secret: String,

    @Value("\${jwt.expiration}")
    private val expiration: Long

) {



    private fun signingKey(): SecretKey =
        Keys.hmacShaKeyFor(secret.toByteArray())

    fun generateToken(userDetails: UserDetails): String {

        return Jwts.builder()
            .subject(userDetails.username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(signingKey())
            .compact()
    }

    fun extractUsername(token: String): String =
        extractClaim(token) { it.subject }

    fun isTokenValid(
        token: String,
        userDetails: UserDetails
    ): Boolean {

        val username = extractUsername(token)

        return username == userDetails.username &&
                !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean =
        extractClaim(token) { it.expiration }
            .before(Date())

    private fun <T> extractClaim(
        token: String,
        resolver: (Claims) -> T
    ): T {

        val claims = Jwts.parser()
            .verifyWith(signingKey())
            .build()
            .parseSignedClaims(token)
            .payload

        return resolver(claims)
    }
}