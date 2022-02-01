package com.example.TaskManagerAPI.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.TaskManagerAPI.entities.Tarea;
import com.example.TaskManagerAPI.entities.Usuario;
import com.example.TaskManagerAPI.models.TareaModel;
import com.example.TaskManagerAPI.models.UsuarioModel;

@Component
public class TareaConverter {
	public TareaModel entityToModel(Tarea tarea) {
		TareaModel tareaModel = new TareaModel();
		tareaModel.setId(tarea.getId());
		tareaModel.setTitulo(tarea.getTitulo());
		tareaModel.setDescripcion(tarea.getDescripcion());
		tareaModel.setEstado(tarea.getEstado());
		tareaModel.setFechaCreacion(tarea.getFechaCreacion());
		UsuarioModel usuarioModel = new UsuarioModel();
		usuarioModel.setEmail(tarea.getUsuario().getEmail());
		tareaModel.setUsuarioModel(usuarioModel);
		return tareaModel;
	}
	
	public Tarea modelToEntity(TareaModel tareaModel) {
		Tarea tarea = new Tarea();
		if(tareaModel.getId()!=0) {
			tarea.setId(tareaModel.getId());
		}
		tarea.setTitulo(tareaModel.getTitulo());
		tarea.setDescripcion(tareaModel.getDescripcion());
		tarea.setEstado(tareaModel.getEstado());
		tarea.setFechaCreacion(tareaModel.getFechaCreacion());
		Usuario usuario = new Usuario();
		usuario.setEmail(tareaModel.getUsuarioModel().getEmail());
		tarea.setUsuario(usuario);
		return tarea;
	}
}
