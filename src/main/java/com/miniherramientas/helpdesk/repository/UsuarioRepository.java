package com.miniherramientas.helpdesk.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.miniherramientas.helpdesk.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String>{

}
