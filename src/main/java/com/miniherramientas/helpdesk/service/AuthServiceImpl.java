package com.miniherramientas.helpdesk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniherramientas.helpdesk.model.Usuario;
import com.miniherramientas.helpdesk.repository.AutenticacionRepository;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	AutenticacionRepository repositorio;
	
	@Override
	public boolean loginUsuario(String usuario, String clave) {
		Optional<Usuario> usuarioConsulta = repositorio.loginusuario(usuario, clave);
		return usuarioConsulta.isPresent();
	}

}
