package com.example.TaskManagerAPI.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.TaskManagerAPI.entities.Tarea;
import com.example.TaskManagerAPI.entities.Usuario;
import com.example.TaskManagerAPI.models.TareaModel;
import com.example.TaskManagerAPI.models.UsuarioModel;

@Component
public class TareaConverter {
	/**
	 * Devuelve el modelo de la tarea a traves de una entidad.
	 * @param tarea Entidad de la que tomamos los datos
	 * @return TareaModel con los datos de la entidad.
	 */
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
	
	/**
	 * Devuelve la entidad de una tarea a traves de un modelo
	 * @param tareaModel Modelo del que tomaremos los datos
	 * @return Tarea con los datos del modelo
	 */
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
