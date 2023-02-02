package com.miniherramientas.helpdesk.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miniherramientas.helpdesk.model.Usuario;
import com.miniherramientas.helpdesk.service.AuthService;
import com.miniherramientas.helpdesk.util.JwtUtil;

@RestController
@RequestMapping("/api/token")
public class AuthController {

	@Autowired
	private AuthService servicio;
	
	@PostMapping()
	public ResponseEntity<?> consultarToken(@RequestBody Usuario usuario) {
		boolean respuesta = servicio.loginUsuario(usuario.getUsuario(), usuario.getClave());
		if (!respuesta) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		//return ResponseEntity.ok(new JSONObject().put("token", JwtUtil.crearToken(usuario.getUsuario(), "admin")));
		String token = JwtUtil.crearToken(usuario.getUsuario(), "Admin");
		System.out.println(token);
		return ResponseEntity.ok(new JSONObject().put("token", token).toString());
	}
}
