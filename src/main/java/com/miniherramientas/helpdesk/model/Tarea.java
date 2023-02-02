package com.miniherramientas.helpdesk.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tareas")
public class Tarea {
	@Id
	private Long id;
	private String descripcion;
	private String estado;

	public Tarea() {
		super();
	}

	public Tarea(Long id, String descripcion, String estado) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(descripcion, estado, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarea other = (Tarea) obj;
		return Objects.equals(descripcion, other.descripcion) && Objects.equals(estado, other.estado)
				&& Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Tarea [id=" + id + ", descripcion=" + descripcion + ", estado=" + estado + "]";
	}
}
