package com.example.TaskManagerAPI.models;

import java.util.List;


import com.example.TaskManagerAPI.entities.Tarea;
import com.example.TaskManagerAPI.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UsuarioModel {
	private String nombre;
	private String apellidos;
	private String email;
	//Esto ignora el campo password pero al pasarselo lo ignoraria y lo pasaria vacio?
	@JsonIgnore
	private String password;
	private List<TareaModel> tareas;
	
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
}
