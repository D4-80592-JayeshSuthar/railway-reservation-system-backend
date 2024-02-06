package com.app.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.app.exceptions.UserNotActiveException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		if (authException instanceof UserNotActiveException ) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "User is not active");
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
	}

}
