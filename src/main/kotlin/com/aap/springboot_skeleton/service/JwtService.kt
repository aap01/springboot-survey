package com.aap.springboot_skeleton.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service


@Service
class JwtService {
    companion object {
        private const val SECRET_KEY = "asPdi34Z9duf4Nd4Ks>._"
        private const val USER_NAME = "username"
    }

    private fun extractClaims(token: String): Claims {
        return Jwts
            .parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .body
    }

    private fun <T> extractClaim(token: String, claimsResolver: (Claims) -> T): T {
        val claims = extractClaims(token)
        return claimsResolver.invoke(claims)
    }

    fun extractUsername(token: String): String? {
        val claims = extractClaims(token)
        return claims[USER_NAME] as String?
    }

    fun generateToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Any>()
        claims[USER_NAME] = userDetails.username
        return Jwts
            .builder()
            .setClaims(claims)
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact()
    }

    fun isTokenValid(token: String, userDetails: UserDetails): Boolean {
        return true
    }

}