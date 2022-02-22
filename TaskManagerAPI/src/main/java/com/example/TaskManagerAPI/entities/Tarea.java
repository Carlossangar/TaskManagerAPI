package com.example.TaskManagerAPI.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	private Date fechaCreacion;
	@Column(name = "descripcion")
	private String descripcion;

	@ManyToOne
	@JsonBackReference
	@JsonIgnoreProperties({"tareas"})
	//@Cascade(CascadeType.ALL)
	Usuario usuario;	

}
