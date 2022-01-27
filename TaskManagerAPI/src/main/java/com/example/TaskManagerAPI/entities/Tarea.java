package com.example.TaskManagerAPI.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tarea")
@Getter @Setter  @NoArgsConstructor
public class Tarea {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	@Column(name = "titulo")
	private String titulo;
	@Column(name = "estado")
	private String estado;
	@Column(name = "fechaCreacion")
	private String fechaCreacion;
	@Column(name = "descripcion")
	private String descripcion;

	@ManyToOne
	@JsonBackReference
	Usuario usuario;

}
