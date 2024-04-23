package com.vitacure.authservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vitacure.authservice.model.User;
import com.vitacure.authservice.model.UserPrincipal;
import com.vitacure.authservice.repository.UserRepository;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findById(username).orElseThrow(
				() -> new UsernameNotFoundException("Invalid Username"));
		return new UserPrincipal(user);
	}
}
