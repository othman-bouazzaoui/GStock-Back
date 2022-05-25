package com.tawdi7atnet.security.util;

import java.util.Arrays;
import java.util.List;

public class JwtUtil {

	public static final String SECRET = "oth.bouazzaoui@gmail.com";
	
	public static final List<String> AUTHENTICATS_PATHS = Arrays.asList("/api/v1/users/refreshToken", "/login");
	
	public static final String[] AUTHORIZED_PATHS = { "/login", "/v3/api-docs/**", "/swagger-ui/**", "/h2-console/**",
			"/api/v1/users/refreshToken" };
	
	public static final String AUTHORIZATION = "Authorization";
	
	public static final String PREFIX = "Bearer ";
	
	//One Minute
	public static final Long EXPIRE_ACCES_TOKEN = System.currentTimeMillis() + 1000 * 60;
	
	//One Year
	public static final int EXPIRE_REFRESH_TOKEN = 1;

}
