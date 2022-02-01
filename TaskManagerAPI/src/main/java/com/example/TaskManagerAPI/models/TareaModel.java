package com.example.TaskManagerAPI.models;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import com.example.TaskManagerAPI.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class TareaModel {
	private long id;
	private String titulo;
	private String estado;
	private String fechaCreacion;
	private String descripcion;
	private UsuarioModel usuarioModel;
	
	@JsonIgnore
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}
}
