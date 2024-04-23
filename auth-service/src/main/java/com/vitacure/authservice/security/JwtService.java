package com.vitacure.authservice.security;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.vitacure.authservice.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

@SuppressWarnings("deprecation")
@Service
public class JwtService {
	
	@Value("${app.SECRETKEY}")
	private String SECRETKEY;

	public String generateToken(User user) {
		
		//Generate claims
		Map<String, Object> claims = new HashMap<>();
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(user.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*30))
				.signWith(getKey(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	private Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRETKEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUserName(String token) {
		Claims claim =	Jwts.parser()
							.setSigningKey(getKey())
							.build()
							.parseClaimsJws(token)
							.getBody();
		return claim.getSubject();
	}
	
	private Date extractExpiration(String token) {
		Claims claim =	Jwts.parser()
				.setSigningKey(getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claim.getExpiration();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	 
}
