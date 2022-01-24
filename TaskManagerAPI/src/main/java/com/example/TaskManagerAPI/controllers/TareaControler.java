package com.example.TaskManagerAPI.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	@GetMapping(path="/tareas")
	public ArrayList<TareaModel> getListaTareas(){
		return tareaService.getTareas();
	}
	
//OPERACIONES POST
	@PostMapping(path="/tarea")
	public void postTarea(@RequestBody TareaModel tareaModel) {
		//validacion de datos.
		if(!(tareaModel.getTitulo().isBlank() || tareaModel.getDescripcion().isBlank() || tareaModel.getEstado().isBlank() || tareaModel.getFechaCreacion().isBlank())) {
			if(!tareaService.addTarea(tareaModel)) {
				throw new BadRequestException();
			}
		} else {
			throw new BadRequestException();
		}
		
	}
//OPERACIONES PUT
//OPERACIONES DELETE
}
