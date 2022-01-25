package com.example.TaskManagerAPI.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TaskManagerAPI.errors.BadRequestException;
import com.example.TaskManagerAPI.errors.NotFoundException;
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
	@PutMapping(path = "/estadoTarea")
	public TareaModel putEstadoTarea(@RequestBody TareaModel tareaModel) {
		//Buscamos la tarea.
		TareaModel tareaAntigua = buscarTarea(tareaModel.getId());
		//comprobamos que existe
		if(tareaAntigua!=null) {
			//Si existe comprobamos que el estado es una de las opciones correctas.
			if(tareaModel.getEstado().equalsIgnoreCase("pendiente")||tareaModel.getEstado().equalsIgnoreCase("en progreso")||tareaModel.getEstado().equalsIgnoreCase("completada")) {
				tareaAntigua.setEstado(tareaModel.getEstado());
				tareaService.actualizarTarea(tareaAntigua);
				return tareaAntigua;
			} else {
				//Si no, devolvemos bad request
				throw new BadRequestException();
			}			
		}
		//Si no existe devolvemos not found
		throw new NotFoundException();
	}
//OPERACIONES DELETE

//Funciones auxiliares
	public TareaModel buscarTarea(long id) {
		return tareaService.getTarea(id);
	}
}
