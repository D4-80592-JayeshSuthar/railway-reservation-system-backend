package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.UserEntityDao;
import com.app.entities.UserEntity;
import com.app.exceptions.UserNotActiveException;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
	// dep : dao layer
	@Autowired
	private UserEntityDao userDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity user = userDao.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("Email not found!!!!"));
		if (!user.isActive()) {
	        throw new UserNotActiveException("User is not active"); // Custom exception
	    }
		
		return new CustomUserDetails(user);
	}

}
