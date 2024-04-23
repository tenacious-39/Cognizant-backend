/**
 * 
 * @author Kritidipta Ghosh
 *
 */

package com.vitacure.authservice.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vitacure.authservice.exception.CustomException;
import com.vitacure.authservice.exception.InvalidJwtToken;
import com.vitacure.authservice.feign.PharmacyAuthClient;
import com.vitacure.authservice.model.Pharmacy;
import com.vitacure.authservice.model.User;
import com.vitacure.authservice.repository.UserRepository;
import com.vitacure.authservice.security.JwtService;
import com.vitacure.authservice.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private PharmacyAuthClient pharmaAuthClient;
	
	@Override
	public User register(User user) throws CustomException {
		Optional<User> userObj = userRepo.findById(user.getUsername());
		
		if(userObj.isPresent()) {
			throw new CustomException("User already exists");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		String role = user.getRole();
		if(role.equals("ROLE_PHARMACY")) {
			Pharmacy pharma = new Pharmacy();
			pharma.setEmail(user.getUsername());
			pharma.setPharmaId(getAlphaNumericString(6));
			pharma.setName("Pharmacy");
			pharma.setLocation("Default");
			pharmaAuthClient.addPharmacyByAuth(pharma);
		}
		
		User addedUser = userRepo.save(user);
		addedUser.setPassword(null);
		return addedUser;
	}
	
	@Override
	public User validateToken(String token) throws InvalidJwtToken {
		if(token.startsWith("Bearer ")) {
			token = token.substring(7);
		}
		if(jwtService.isTokenExpired(token)) {
			throw new InvalidJwtToken();
		}
		
		String username = jwtService.extractUserName(token);
		if(username == null) {
			throw new InvalidJwtToken();
		}
		User user = userRepo.findById(username).orElseThrow(
				() -> new InvalidJwtToken());
		user.setPassword(null);
		return user;
	}
	
	 private static String getAlphaNumericString(int n) { 
	 
		  // choose a Character random from this String 
		  String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		         + "0123456789"
		         + "abcdefghijklmnopqrstuvxyz"; 
		 
		  // create StringBuffer size of AlphaNumericString 
		  StringBuilder sb = new StringBuilder(n); 
		 
		  for (int i = 0; i < n; i++) { 
		 
		   // generate a random number between 
		   // 0 to AlphaNumericString variable length 
		   int index 
		    = (int)(AlphaNumericString.length() 
		      * Math.random()); 
		 
		   // add Character one by one in end of sb 
		   sb.append(AlphaNumericString 
		      .charAt(index)); 
		  } 
		 
		  return sb.toString(); 
	 }
}
