package com.miniherramientas.helpdesk.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("usuarios")
public class Usuario {
	@Id
	private String usuario;
	private String clave;
	private Long idTarea;

	public Usuario() {
		super();
	}

	public Usuario(String usuario, String clave, Long idTarea) {
		super();
		this.usuario = usuario;
		this.clave = clave;
		this.idTarea = idTarea;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public Long getIdTarea() {
		return idTarea;
	}

	public void setIdTarea(Long idTarea) {
		this.idTarea = idTarea;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clave, idTarea, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(clave, other.clave) && Objects.equals(idTarea, other.idTarea)
				&& Objects.equals(usuario, other.usuario);
	}

	@Override
	public String toString() {
		return "Usuario [usuario=" + usuario + ", clave=" + clave + ", idTarea=" + idTarea + "]";
	}
}
