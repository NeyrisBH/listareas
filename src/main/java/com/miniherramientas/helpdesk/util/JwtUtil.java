package com.miniherramientas.helpdesk.util;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	public static final String KEYWORD="PALABRACLAVE";
	
	public static String crearToken(String usuario, String rol) {
		List<GrantedAuthority> grantedAuthories = AuthorityUtils
				.commaSeparatedStringToAuthorityList("SCOPED_"+rol);
		String jwt = Jwts.builder()
				.setId("jwt"+usuario)
				.claim("usuario", usuario)
				.claim("authorities", grantedAuthories.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 30000000))
				.signWith(SignatureAlgorithm.HS512, KEYWORD.getBytes())
				.compact();
		return jwt;
	}
}
