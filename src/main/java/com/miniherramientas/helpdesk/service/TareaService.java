package com.miniherramientas.helpdesk.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.miniherramientas.helpdesk.model.Tarea;

@Service
public interface TareaService {
	public List<Tarea> consultarTareas();
	public Optional<Tarea> consultarTareaPorId(Long id);
	public Optional<Tarea> consultarTareaPorDescripcion(String descripcion);
	public Tarea crearTarea(Tarea tarea);
	public Tarea actualizarTarea(Tarea tarea);
	public String eliminarTareaPorId(Long id);
}
