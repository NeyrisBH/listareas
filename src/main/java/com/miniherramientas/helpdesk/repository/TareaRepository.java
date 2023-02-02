package com.miniherramientas.helpdesk.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.miniherramientas.helpdesk.model.Tarea;

@Repository
public interface TareaRepository extends MongoRepository<Tarea, Long> {
	@Query("{estado: '?0'}")
	Optional<Tarea> findByEstado(String estado);
	
	@Query("{estado: '?0'}")
	List<Tarea> findByEstadoConsulta(String estado);
}
