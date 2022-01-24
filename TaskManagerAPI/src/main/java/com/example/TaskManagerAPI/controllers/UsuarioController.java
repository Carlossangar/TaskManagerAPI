package com.example.TaskManagerAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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
	//POST
	@PostMapping(path="/usuario")
	public void postUsuario(@RequestBody UsuarioModel usuarioModel) {
		//Validacion de sus datos.
		if(!(usuarioModel.getNombre().isBlank() || usuarioModel.getApellidos().isBlank() || usuarioModel.getEmail().isBlank() || usuarioModel.getPassword().isBlank())) {
			//Insercion del usuario.
			if(!usuarioService.addUsuario(usuarioModel)) {
				throw new BadRequestException();
			}
		} else {
			throw new BadRequestException();
		}
	}
	//PUT
	//DELETE
}