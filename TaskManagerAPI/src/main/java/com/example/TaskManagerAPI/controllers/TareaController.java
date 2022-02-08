package com.example.TaskManagerAPI.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
public class TareaController {
	
	@Autowired
	private TareaService tareaService;
	
//OPERACIONES GET
	/**
	 * Pide al service todas las tareas.
	 * @return ArrayList<TareaModel> con todas las tareas.
	 */
	@GetMapping(path="/tareas")
	public ArrayList<TareaModel> getListaTareas(){
		return tareaService.getTareas();
	}
	/**
	 * Pide al service todas las tareas con un mismo estado.
	 * @param status Estado de la tarea por la que filtraremos
	 * @return ArrayList<TareaModel> con las tareas que tienen su mismo estado.
	 */
	@GetMapping(path="/tareas/{status}")
	public ArrayList<TareaModel> getTareasPorEstado(@PathVariable(name="status") String status){
		if(status.equalsIgnoreCase("pendiente") || status.equalsIgnoreCase("en progreso") || status.equalsIgnoreCase("completada")) {
			return tareaService.getTaskByStatus(status.toLowerCase());
		} else {
			throw new BadRequestException();
		}
	}
	/**
	 * Pide al service todas las tareas de un usuario.
	 * @param email ID del empleado
	 * @return ArrayList<TareaModel> con las tareas de un usuario.
	 */
	@GetMapping(path="/tareasUsuario/{email}")
	public ArrayList<TareaModel> getListaTareas(@PathVariable(name="email") String email){
		return tareaService.getTareasUsuario(email);
	}
	
//POST
	/**
	 * Manda al service una tarea para que sea registrada en el sistema.
	 * @param tareaModel Body de la peticion con los datos de la tarea.
	 */
	@PostMapping(path="/tarea")
	public void postTarea(@RequestBody TareaModel tareaModel) {
		//validacion de datos. COMPLETAR CON LO QUE FALTA.
		if(!( tareaModel.getTitulo().isBlank() || tareaModel.getDescripcion().isBlank() || tareaModel.getEstado().isBlank() )) {
			if(!tareaService.addTarea(tareaModel)) {
				throw new BadRequestException();
			}
		} else {
			throw new BadRequestException();
		}
		
	}
	
//PUT
	/**
	 * Manda al service una tarea para que sea modificada en la base de datos.
	 * @param tareaModel Body de la peticion con los datos de la tarea.
	 * @return TareaModel modificada.
	 */
	@PutMapping(path = "/estadoTarea")
	public TareaModel putEstadoTarea(@RequestBody TareaModel tareaModel) {
		//Buscamos la tarea.
		Optional<TareaModel> tareaAntigua = buscarTarea(tareaModel.getId());
		//comprobamos que existe
		if(tareaAntigua.isPresent()) {
			//Si existe comprobamos que el estado es una de las opciones correctas.
			if(tareaModel.getEstado().equalsIgnoreCase("pendiente")||tareaModel.getEstado().equalsIgnoreCase("en progreso")||tareaModel.getEstado().equalsIgnoreCase("completada")) {
				tareaAntigua.get().setEstado(tareaModel.getEstado());
				tareaService.actualizarTarea(tareaAntigua.get());
				return tareaAntigua.get();
			} else {
				//Si no, devolvemos bad request
				throw new BadRequestException();
			}			
		}
		//Si no existe devolvemos not found
		throw new NotFoundException();
	}
	
//DELETE
	/**
	 * Solicita al service que se elimine la tarea.
	 * @param id identificador de la tarea para buscarla en la base de datos.
	 * @return TareaModel que se elimino.
	 */
	@DeleteMapping(path="/tarea/{id}")
	public TareaModel deleteTarea(@PathVariable(name="id") long id) {
		Optional<TareaModel> resultado = tareaService.deleteTarea(id);
		if(resultado.isPresent()) {
			return resultado.get();
		} else {
			throw new NotFoundException();
		}
	}
	
//Funciones auxiliares
	/**
	 * Funcion que busca en el service una tarea por su identificador
	 * @param id identificador de la tarea que deseamos buscar.
	 * @return TareaModel con los datos de la tarea solicitada por el usuario.
	 */
	public Optional<TareaModel> buscarTarea(long id) {
		return tareaService.getTarea(id);
	}
}
