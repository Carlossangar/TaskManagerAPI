package com.example.TaskManagerAPI.converters;

import org.springframework.stereotype.Component;

import com.example.TaskManagerAPI.entities.Usuario;
import com.example.TaskManagerAPI.models.UsuarioModel;

@Component
public class UsuarioConverter {
	//nos devuelde el modelo del usuario (lo que enviaremos fuera del api)
	public UsuarioModel entityToModel(Usuario usuario) {
		UsuarioModel usuarioModel = new UsuarioModel();
		usuarioModel.setNombre(usuario.getNombre());
		usuarioModel.setApellidos(usuario.getApellidos());
		usuarioModel.setEmail(usuario.getEmail());
		usuarioModel.setTareas(usuario.getTareas());
		return usuarioModel;
	}
	
	//nos devuelve el entity del usuario (lo que enviaremos a la base de datos)
	public Usuario modelToEntity(UsuarioModel usuarioModel) {
		Usuario usuario = new Usuario();
		usuario.setNombre(usuarioModel.getNombre());
		usuario.setApellidos(usuarioModel.getApellidos());
		usuario.setEmail(usuarioModel.getEmail());
		usuario.setPassword(usuarioModel.getPassword());
		return usuario;
	}
}
