package com.miniherramientas.helpdesk.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miniherramientas.helpdesk.model.Tarea;
import com.miniherramientas.helpdesk.repository.TareaRepository;
@Service
public class TareaServiceImpl implements TareaService {

	@Autowired
	private TareaRepository repositorio;
	
	@Override
	public List<Tarea> consultarTareas() {
		return repositorio.findAll();
	}

	@Override
	public Optional<Tarea> consultarTareaPorId(Long id) {
		if (id == null) {
			return Optional.empty();
		}
		return repositorio.findById(id);
	}

	@Override
	public Optional<Tarea> consultarTareaPorDescripcion(String descripcion) {
		if (descripcion == null) {
			return Optional.empty();
		}
		return  repositorio.findByEstado(descripcion);
	}

	@Override
	public Tarea crearTarea(Tarea tarea) {
		Optional<Tarea> consulta = repositorio.findById(tarea.getId());
		if (consulta.isPresent()) {
			return null;
		}
		List<Tarea> tareas = repositorio.findByEstadoConsulta(tarea.getDescripcion());
		if (tareas.size() > 0) {
			return tareas.get(0); 
		}
		return repositorio.save(tarea);
	}

	@Override
	public Tarea actualizarTarea(Tarea tarea) {
		Optional<Tarea> consulta = repositorio.findById(tarea.getId());
		if (consulta.isPresent()) {
			return repositorio.save(tarea);
		} else {
			return null;
		}
	}

	@Override
	public String eliminarTareaPorId(Long id) {
		Optional<Tarea> consulta = repositorio.findById(id);
		if (consulta.isPresent()) {
			repositorio.delete(consulta.get());
			return "Registro eliminado correctamente";
		} else {
			return "Registro no encontrado, no se puede eliminar";
		}
	}
}
