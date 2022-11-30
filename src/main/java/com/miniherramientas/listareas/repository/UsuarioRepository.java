package com.miniherramientas.listareas.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.miniherramientas.listareas.model.Usuario;

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String>{

}
