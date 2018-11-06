package com.bolsadeideas.springboot.app.auth.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.boot.model.source.spi.SingularAttributeNature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.HttpMediaTypeException;

import com.bolsadeideas.springboot.app.auth.service.JWTService;
import com.bolsadeideas.springboot.app.models.entity.User;
import com.bolsadeideas.springboot.app.util.objects.ObjectsUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTService jwtService;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService; 
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", HttpMethod.POST.name()));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String username = obtainUsername(request);
		String password = obtainPassword(request);

		if (ObjectsUtils.allNotNull(username, password)) {
			logger.info("Username desde Form-Data :" + username.concat("-").concat(password));
		} else {
			User user = null;
			try {
				user = new ObjectMapper().readValue(request.getInputStream(), User.class);
				username = user.getUsername();
				password = user.getPassword();
				logger.info("Username desde Raw :" + username.concat("-").concat(password));

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		username = username.trim();

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		return authenticationManager.authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String token = jwtService.create(authResult);
		
		response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

		Map<String, Object> mapToken = new HashMap<>();
		mapToken.put("token", token);
		mapToken.put("user", (org.springframework.security.core.userdetails.User) authResult.getPrincipal());
		mapToken.put("message", "Inicio Successfuly");

		response.getWriter().write(new ObjectMapper().writeValueAsString(mapToken));
		response.setStatus(200);
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);

	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		Map<String, Object> body = new HashMap<String, Object>();
		body.put("mensaje", "Error de autenticación: username o password incorrecto!");
		body.put("error", failed.getMessage());
		
		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType("application/json");
	}
}
