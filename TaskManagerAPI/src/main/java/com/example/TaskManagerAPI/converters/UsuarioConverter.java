package com.example.TaskManagerAPI.converters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.TaskManagerAPI.entities.Tarea;
import com.example.TaskManagerAPI.entities.Usuario;
import com.example.TaskManagerAPI.models.TareaModel;
import com.example.TaskManagerAPI.models.UsuarioModel;

@Component
public class UsuarioConverter {
	
	/**
	 * Devuelve el modelo del usuario a traves de una entidad.
	 * @param usuario Entidad de la que tomamos los datos
	 * @return UsuarioModel con los datos de la entidad,
	 */
	public UsuarioModel entityToModel(Usuario usuario) {
		UsuarioModel usuarioModel = new UsuarioModel();
		usuarioModel.setNombre(usuario.getNombre());
		usuarioModel.setApellidos(usuario.getApellidos());
		usuarioModel.setEmail(usuario.getEmail());
		return usuarioModel;
	}
	
	/**
	 * Devuelve la entidad de un usuario a traves de un modelo.
	 * @param usuarioModel Modelo del que tomaremos los datos
	 * @return Usuario con los datos del modelo.
	 */
	public Usuario modelToEntity(UsuarioModel usuarioModel) {
		Usuario usuario = new Usuario();
		usuario.setNombre(usuarioModel.getNombre());
		usuario.setApellidos(usuarioModel.getApellidos());
		usuario.setEmail(usuarioModel.getEmail());
		usuario.setPassword(usuarioModel.getPassword());
		return usuario;
	}
}
