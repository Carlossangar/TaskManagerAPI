package com.example.TaskManagerAPI.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.example.TaskManagerAPI.entities.Tarea;
import com.example.TaskManagerAPI.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class UsuarioModel {
	private long userID;
	private String nombre;
	private String apellidos;
	private String email;
	private String password;
	List<Tarea> tareas;
}
