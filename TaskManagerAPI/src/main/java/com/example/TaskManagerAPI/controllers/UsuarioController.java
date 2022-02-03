package com.example.TaskManagerAPI.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaskManagerAPI.errors.BadRequestException;
import com.example.TaskManagerAPI.models.UsuarioModel;
import com.example.TaskManagerAPI.services.UsuarioService;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	//GET
	/**
	 * Solicita al service una lista con todos los usuarios registrados.
	 * @return List<UsuarioModel> con todos los usuarios registrados en la base de datos.
	 */
	@GetMapping(path="/usuarios")
	public List<UsuarioModel> getUsuarios(){
		return usuarioService.getUsuarios();
	}
	
	//POST
	/**
	 * Envia al service un nuevo usuario para que lo registre.
	 * @param usuarioModel contiene los datos del uusuario que vamos a registrar.
	 */
	@PostMapping(path="/usuario")
	public void postUsuario(@RequestBody UsuarioModel usuarioModel) {
		//Validacion de sus datos.
		if(!(usuarioModel.getNombre().isBlank() || usuarioModel.getApellidos().isBlank() || usuarioModel.getEmail().isBlank())) {
			//Insercion del usuario.
			if(!usuarioService.addUsuario(usuarioModel)) {
				throw new BadRequestException();
			}
		} else {
			throw new BadRequestException();
		}
	}
}
