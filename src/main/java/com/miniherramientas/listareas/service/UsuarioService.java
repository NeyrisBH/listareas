package com.miniherramientas.listareas.service;

import java.util.List;
import java.util.Optional;
import com.miniherramientas.listareas.model.Usuario;

public interface UsuarioService {
	public List<Usuario> consultar();
	public Optional<Usuario> consultarUsuario(String usuario);
	public Usuario crear(Usuario usuario);
	public Usuario actualizar(Usuario usuario);
	public String eliminar(String usuario);
}
