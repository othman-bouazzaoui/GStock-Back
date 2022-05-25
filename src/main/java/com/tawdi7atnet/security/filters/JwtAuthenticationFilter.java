package com.tawdi7atnet.security.filters;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tawdi7atnet.security.util.JwtUtil;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		LOG.info("I am in attemptAuthentication ");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		System.out.println("username " + username);
		System.out.println("password " + password);

		UsernamePasswordAuthenticationToken authenticationFilter = new UsernamePasswordAuthenticationToken(username,
				password);

		return authenticationManager.authenticate(authenticationFilter);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		LOG.info("I am in successfulAuthentication ");

		User user = (User) authResult.getPrincipal();
		LOG.info("User : " + user);

		Algorithm algorithm = Algorithm.HMAC256(JwtUtil.SECRET);
		
		/* Access Token */
		String accesToken = JWT.create().withSubject(user.getUsername())
				.withExpiresAt(new Date(JwtUtil.EXPIRE_ACCES_TOKEN))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("roles",
						user.getAuthorities().stream().map(ga -> ga.getAuthority()).collect(Collectors.toList()))
				.sign(algorithm);
		
		/* 
		 * Refresh Token : je l'utilise pour renouveler mon acces-token en cas d'expiration 
		 * pour eviter de ne pas envoyer le username et le mot de passe à chaque fois
		 * C'est presque le même comportement de "remembre me" 
		 * Sauf qu'avec le refresh-token on pourront que renouvler le acces token et non faire accéder à des ressources sécurisés
		 * Expire after 1 Year
		 */
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, +JwtUtil.EXPIRE_REFRESH_TOKEN);
		
		String refreshToken = JWT.create().withSubject(user.getUsername())
				.withExpiresAt(new Date(calendar.getTimeInMillis()))
				.withIssuer(request.getRequestURL().toString())
				.sign(algorithm);

		LOG.info("acces_Token : {}",accesToken);
		LOG.info("refresh_Token : {}",refreshToken);
		
		Map<String, String> idToken = new HashMap<>();
		idToken.put("acces_Token", accesToken);
		idToken.put("refresh_Token", refreshToken);
		
		response.setContentType("application/json");
		
		new ObjectMapper().writeValue(response.getOutputStream(),idToken);
		
		super.successfulAuthentication(request, response, chain, authResult);
	}

}
