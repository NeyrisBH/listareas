package com.miniherramientas.helpdesk.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniherramientas.helpdesk.model.Usuario;
import com.miniherramientas.helpdesk.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository repository;

	@Override
	public List<Usuario> consultar() {
		return repository.findAll();
	}

	@Override
	public Optional<Usuario> consultarUsuario(String usuario) {
		if (usuario == null) {
			return Optional.empty();
		}
		return repository.findById(usuario);
	}

	@Override
	public Usuario crear(Usuario usuario) {
		if (usuario == null) {
			return null;
		}
		return repository.insert(usuario);
	}

	@Override
	public Usuario actualizar(Usuario usuario) {
		if (usuario == null) {
			return null;
		}
		return repository.save(usuario);
	}

	@Override
	public String eliminar(String usuario) {
		if (usuario != null) {
			repository.deleteById(usuario);
			return "OK";
		}
		return null;
	}
}
