package com.example.TaskManagerAPI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskManagerAPI.converters.TareaConverter;
import com.example.TaskManagerAPI.entities.Tarea;
import com.example.TaskManagerAPI.entities.Usuario;
import com.example.TaskManagerAPI.models.TareaModel;
import com.example.TaskManagerAPI.repositories.TareaRepository;
import com.example.TaskManagerAPI.repositories.UsuarioRepository;

@Service
public class TareaService {

	@Autowired
	private TareaRepository tareaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private TareaConverter tareaConverter;
	
	/**
	 * Funcion que comprueba si la tarea existe ya en la base de datos, en caso negativo introduce la tareaModel.
	 * @param tareaModel
	 * @return true si se pudo introducir, false en caso de existir ya dentro de la base de datos.
	 */
	public boolean addTarea(TareaModel tareaModel) {
		Tarea tarea = tareaConverter.modelToEntity(tareaModel);
		if(!tareaRepository.existsById(tarea.getId())) {
			tareaRepository.save(tarea);
			return true;
		}
		return false;
	}
	
	/**
	 * Funcion que devuelve una lista de todas las tareas de la base de datos.
	 * @return ArrayList de tipo TareaModel que contiene todas las tareas publicadas en la base de datos.
	 */
	public ArrayList<TareaModel> getTareas(){
		List<Tarea> tareas = tareaRepository.findAll();
		ArrayList<TareaModel> listaTareasModel = new ArrayList<>();
		for (Tarea task : tareas) {
			TareaModel taskModel = tareaConverter.entityToModel(task);
			listaTareasModel.add(taskModel);
		}
		return listaTareasModel;
	}
	
	/**
	 * Devuelve una lista de todas las tareas que se encuentran bajo un estado en especifico.
	 * @param state Estado de la tarea por el que las vamos a filtrar.
	 * @return ArrayList de tipo TareaModel con todas las tareas filtradas por su estado.
	 */
	public ArrayList<TareaModel> getTaskByStatus(String state){
		List<Tarea> tareas = tareaRepository.findByEstado(state);
		ArrayList<TareaModel> tareasModel = new ArrayList<>();
		for (Tarea tarea : tareas) {
			tareasModel.add(tareaConverter.entityToModel(tarea));
		}
		return tareasModel;
	}
	
	/**
	 * Devuelve una tarea en especifico que buscamos por su id
	 * @param id Identificador de la tarea por la que lo buscamos
	 * @return Optional<TareaModel> encontrada con ese id o vacio.
	 */
	public Optional<TareaModel> getTarea(long id) {
		Optional<TareaModel> tareaSolicitada = Optional.empty();
		//Extraemos un optional de la base de datos
		Optional<Tarea> optionalTarea = tareaRepository.findById(id);
		//Comprobamos si existe la tarea
		if(optionalTarea.isPresent()) {
			//En caso de que si, devolvemos el usuario.
			tareaSolicitada = Optional.of(tareaConverter.entityToModel(optionalTarea.get()));
		}
		//En caso de que no, devolvemos un null
		return tareaSolicitada;
	}
	
	/**
	 * Devuelve las tareas asignadas a un usuario en concreto.
	 * @param email Clave por la que filtraremos las tareas.
	 * @return ArrayList con las TareaModel que tiene asignadas un usuario.
	 */
	public ArrayList<TareaModel> getTareasUsuario(String email){
		List<Tarea> tareas = new ArrayList<>();
		if(usuarioRepository.existsById(email)) {
			tareas = usuarioRepository.getById(email).getTareas();
			ArrayList<TareaModel> tareasModel = new ArrayList();
			for (Tarea tarea : tareas) {
				tareasModel.add(tareaConverter.entityToModel(tarea));
			}
			return tareasModel;
		} else {
			return null;
		}
	}
	
	/**
	 * Actualiza la tarea a traves del repositorio
	 * @param tareaModel Tarea que vamos a actualizar
	 */
	public void actualizarTarea(TareaModel tareaModel) {
		tareaRepository.save(tareaConverter.modelToEntity(tareaModel));
	}
	
	/**
	 * Elimina una tarea de la base de datos.
	 * @param id Identificador a traves del que buscamos la tarea.
	 * @return Optional<TareaModel> con la tarea eliminada o vacio.
	 */
	public Optional<TareaModel> deleteTarea(long id) {
		Optional<TareaModel> resultadoTM = Optional.empty();
		Optional<Tarea> resultado = tareaRepository.findById(id);
		if(resultado.isPresent()) {
			tareaRepository.delete(resultado.get());
			resultadoTM = Optional.of(tareaConverter.entityToModel(resultado.get()));
		}
		return resultadoTM;
	}
}
