package com.miniherramientas.helpdesk;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.miniherramientas.helpdesk.util.JwtUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.UnsupportedJwtException;

public class JwtFilter extends OncePerRequestFilter {
	private final String HEADER = "Authorization";
	private final String PREFIX = "Bearer ";

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String headers = request.getHeader(HEADER);
			if (headers == null) {
				SecurityContextHolder.clearContext();
			} else if (headers.startsWith(PREFIX)) {
				String token = headers.replace(PREFIX, "");
				Claims contenido = Jwts.parser().setSigningKey(JwtUtil.KEYWORD.getBytes())
						.parseClaimsJws(token).getBody();
				String usuario = (String) contenido.get("usuario");
				System.out.println(usuario);
				List<String> rolesString = (List<String>) contenido.get("authorities");
				UsernamePasswordAuthenticationToken autorizacion = new UsernamePasswordAuthenticationToken(usuario,
						null, rolesString.stream().map(SimpleGrantedAuthority:: new ).collect(Collectors.toList()));
				SecurityContextHolder.getContext().setAuthentication(autorizacion);
			} else {
				SecurityContextHolder.clearContext();
			}
			filterChain.doFilter(request, response);
		} catch (ExpiredJwtException | UnsupportedJwtException e) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
		}
	}
}
