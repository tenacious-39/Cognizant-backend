package com.vitacure.authservice.service;


import com.vitacure.authservice.model.User;

/**
 * 
 * @author Kritidipta Ghosh
 *
 */


public interface AuthService {
	
	/**
	 * Creates new {@link User} in the database.
	 * 
	 * @param user must not be {@literal null}
	 * @return the user after insertion.
	 */
	public User register(User user);
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public User validateToken(String token);

}
