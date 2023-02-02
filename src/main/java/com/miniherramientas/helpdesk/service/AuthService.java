package com.miniherramientas.helpdesk.service;

import org.springframework.stereotype.Service;

@Service
public interface AuthService {
	public boolean loginUsuario(String usuario, String clave);
}
