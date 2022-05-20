package com.tawdi7atnet.security.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/*
 * Ce filter s'execute a chaque fois qu'on demande une ressource
 * pour vérifier a ce que l'utilisateur est Authentifié ou non
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	public static final Logger LOG = LoggerFactory.getLogger(JwtAuthorizationFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (request.getServletPath().equals("/api/v1/users/refreshToken") || request.getServletPath().equals("/login")) {

			// passe au filter suivant
			filterChain.doFilter(request, response);
		} else {

			String authorizationToken = request.getHeader("Authorization");
			if (authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
				try {
					String jwt = authorizationToken.substring(7);
					Algorithm algorithm = Algorithm.HMAC256("Othman1995");
					JWTVerifier jwtVerifier = JWT.require(algorithm).build();
					DecodedJWT verify = jwtVerifier.verify(jwt);

					String username = verify.getSubject();

					LOG.info(" Username : {}", username);

					String[] roles = verify.getClaim("roles").asArray(String.class);

					List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

					for (String r : roles) {
						LOG.info(" Roles : {}", r);
						authorities.add(new SimpleGrantedAuthority(r));
					}

					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							username, null, authorities);

					SecurityContextHolder.getContext().setAuthentication(authenticationToken);

					filterChain.doFilter(request, response);

				} catch (Exception ex) {

					response.setHeader("error-message", ex.getMessage());
					response.sendError(HttpServletResponse.SC_FORBIDDEN);

				}

			} else {
				filterChain.doFilter(request, response);
			}
		}

	}

}
