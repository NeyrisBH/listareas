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
import com.miniherramientas.listareas.model.Tarea;
import com.miniherramientas.listareas.service.TareaService;

@RestController
@RequestMapping("/api/tareas")
public class TareaController {
	@Autowired
	private TareaService servicio;

	@GetMapping
	public ResponseEntity<?> consultarTareas() {
		return ResponseEntity.status(HttpStatus.OK).body(servicio.consultarTareas());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> consultarTareaPorId(@PathVariable Long id) {
		Optional<Tarea> respuesta = servicio.consultarTareaPorId(id);
		if (respuesta.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(respuesta.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping("/d/{descripcion}")
	public ResponseEntity<?> consultarPorDescripcion(@PathVariable String descripcion) {
		Optional<Tarea> tarea = servicio.consultarTareaPorDescripcion(descripcion);
		if (tarea.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(tarea.get());
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
	}

	@PostMapping
	public ResponseEntity<?> crearTarea(@RequestBody Tarea tarea) {
		Optional<Tarea> tareas = servicio.consultarTareaPorId(tarea.getId());
		if (tareas.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(tareas.get());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(servicio.crearTarea(tarea));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> actualizarTarea(@RequestBody Tarea tarea, @PathVariable Long id) {
		Optional<Tarea> tareas = servicio.consultarTareaPorId(id);
		if (tareas.isPresent()) {
			if (tarea.getId().equals(id)) {
				return ResponseEntity.status(HttpStatus.OK).body(servicio.actualizarTarea(tarea));
			} else {
				JSONObject mensajeErrorPorId = new JSONObject();
				mensajeErrorPorId.put("Mensaje", "El id de la tarea no corresponde");
			}
		} else {
			JSONObject mensajeError = new JSONObject();
			mensajeError.put("Mensaje", "La tarea consultado no tiene registro en la base de datos");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensajeError);
		}
		return null;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarTarea(@PathVariable Long id) {
		Optional<Tarea> tareaEliminar = servicio.consultarTareaPorId(id);
		if (tareaEliminar.isPresent()) {
			servicio.eliminarTareaPorId(id);
			JSONObject mensajeEliminar = new JSONObject();
			mensajeEliminar.put("Mensaje", "La tarea fue eliminado con exito");
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		JSONObject mensajeError = new JSONObject();
		mensajeError.put("Mensaje", "Tarea no encontrada, no se pudo eliminar");
		return ResponseEntity.status(HttpStatus.OK).build();
	}	
}
