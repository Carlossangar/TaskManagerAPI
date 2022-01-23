package com.example.TaskManagerAPI.converters;

import org.springframework.stereotype.Component;

import com.example.TaskManagerAPI.entities.Tarea;
import com.example.TaskManagerAPI.models.TareaModel;

@Component
public class TareaConverter {
	public TareaModel entityToModel(Tarea tarea) {
		TareaModel tareaModel = new TareaModel();
		tareaModel.setId(tarea.getId());
		tareaModel.setTitulo(tarea.getTitulo());
		tareaModel.setDescripcion(tarea.getDescripcion());
		tareaModel.setEstado(tarea.getEstado());
		tareaModel.setFechaCreacion(tarea.getFechaCreacion());
		return tareaModel;
	}
	
	public Tarea modelToEntity(TareaModel tareaModel) {
		Tarea tarea = new Tarea();
		tarea.setTitulo(tareaModel.getTitulo());
		tarea.setDescripcion(tareaModel.getDescripcion());
		tarea.setEstado(tareaModel.getEstado());
		tarea.setFechaCreacion(tareaModel.getFechaCreacion());
		return tarea;
	}
}
