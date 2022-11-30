package com.miniherramientas.listareas.controller;

import java.util.Optional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.miniherramientas.listareas.model.Usuario;
import com.miniherramientas.listareas.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	@Autowired
	UsuarioService servicio;

	@GetMapping
	public ResponseEntity<?> consultarUsuarios() {
		return ResponseEntity.status(HttpStatus.OK).body(servicio.consultar());
	}

	@GetMapping("/{usuario}")
	public ResponseEntity<?> consultarUsuarioPorId(@PathVariable String usuario) {
		Optional<Usuario> respuesta = servicio.consultarUsuario(usuario);
		if (respuesta.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(respuesta.get());
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PostMapping
	public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
		Optional<Usuario> usuarios = servicio.consultarUsuario(usuario.getUsuario());
		if (usuarios.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crear(usuario));
	}

	@PutMapping("/{usaurio}")
	public ResponseEntity<?> actualizarTarea(@RequestBody Usuario usuario, @PathVariable String user) {
		Optional<Usuario> usuarios = servicio.consultarUsuario(user);
		if (usuarios.isPresent()) {
			if (usuario.getUsuario().equals(user)) {
				return ResponseEntity.status(HttpStatus.OK).body(servicio.actualizar(usuario));
			} else {
				JSONObject mensajeErrorPorId = new JSONObject();
				mensajeErrorPorId.put("Mensaje", "El usuario no corresponde");
			}
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{usuario}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable String usuario) {
		Optional<Usuario> usuarioEliminar = servicio.consultarUsuario(usuario);
		if (usuarioEliminar.isPresent()) {
			return ResponseEntity.ok(servicio.eliminar(usuario));
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
