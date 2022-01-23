package com.example.TaskManagerAPI.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaskManagerAPI.errors.BadRequestException;
import com.example.TaskManagerAPI.models.TareaModel;
import com.example.TaskManagerAPI.services.TareaService;

@RestController
public class TareaControler {
	@Autowired
	private TareaService tareaService;
//OPERACIONES GET
//OPERACIONES POST
	@PostMapping(path="/tarea")
	public void postTarea(@RequestBody TareaModel tareaModel) {
		//validacion de datos.
		if(!(tareaModel.getTitulo().isBlank() || tareaModel.getDescripcion().isBlank() || tareaModel.getEstado().isBlank() || tareaModel.getFechaCreacion().isBlank())) {
			if(!tareaService.addTarea(tareaModel)) {
				throw new BadRequestException();
			}
		}
		throw new BadRequestException();
	}
//OPERACIONES PUT
//OPERACIONES DELETE
}
