package com.example.TaskManagerAPI.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskManagerAPI.converters.UsuarioConverter;
import com.example.TaskManagerAPI.entities.Usuario;
import com.example.TaskManagerAPI.models.UsuarioModel;
import com.example.TaskManagerAPI.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private UsuarioConverter usuarioConverter;
	
	public boolean addUsuario(UsuarioModel usuarioModel) {
		Usuario usuario = usuarioConverter.modelToEntity(usuarioModel);
		if(!usuarioRepository.findById(usuario.getEmail()).isPresent()) {
			usuarioRepository.save(usuario);
			return true;
		}
		return false;
	}
	
	public ArrayList<UsuarioModel> getUsuarios(){
		List<Usuario> usuarios = usuarioRepository.findAll();
		ArrayList<UsuarioModel> usuariosModel = new ArrayList<>();
		for (Usuario usuario : usuarios) {
			usuariosModel.add(usuarioConverter.entityToModel(usuario));
		}
		return usuariosModel;
	}
}
