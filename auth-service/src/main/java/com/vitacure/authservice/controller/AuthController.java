package com.vitacure.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitacure.authservice.dto.ApiResponse;
import com.vitacure.authservice.dto.LoginResponse;
import com.vitacure.authservice.dto.SignupResponse;
import com.vitacure.authservice.model.User;
import com.vitacure.authservice.security.JwtService;
import com.vitacure.authservice.service.AuthService;


/**
 * 
 * @author Kritidipta Ghosh
 *
 */

@RestController
@RequestMapping("/auth")
//@CrossOrigin("*")
public class AuthController {
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	@GetMapping("/health")
	public String health() {
		System.out.println("hello");
		return "Health is OK";
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> register(@RequestBody User user){
		return new ResponseEntity<SignupResponse>(
				new SignupResponse(true, "User Details", authService.register(user)),
				HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user){
		//Authentication stores the Principal
		// after authenticating the user using AutheticationManager
		//UsernamePasswordAuthenticationToken also implements Authentication
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		
		if(authentication.isAuthenticated())
			return new ResponseEntity<LoginResponse>(
				new LoginResponse(true, 
						jwtService.generateToken(user), 
						"User logged in successfully!!"),
				HttpStatus.OK);
		return new ResponseEntity<LoginResponse>(
				new LoginResponse(false, null, "Invalid Credentials"),
				HttpStatus.FORBIDDEN);
	}
	
	@GetMapping("/validate/{token}")
	public ResponseEntity<?> validateToken(@PathVariable String token){
		User user = authService.validateToken(token);
		return new ResponseEntity<ApiResponse>(
				new ApiResponse(true, "User details", user),
				HttpStatus.OK
			);
	}
}
