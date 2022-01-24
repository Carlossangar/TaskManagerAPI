package com.example.TaskManagerAPI.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.TaskManagerAPI.converters.TareaConverter;
import com.example.TaskManagerAPI.entities.Tarea;
import com.example.TaskManagerAPI.models.TareaModel;
import com.example.TaskManagerAPI.repositories.TareaRepository;

@Service
public class TareaService {

	@Autowired
	private TareaRepository tareaRepository;
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
	
	public ArrayList<TareaModel> getTareas(){
		List<Tarea> tareas = tareaRepository.findAll();
		ArrayList<TareaModel> listaTareasModel = new ArrayList<>();
		for (Tarea task : tareas) {
			TareaModel taskModel = tareaConverter.entityToModel(task);
			listaTareasModel.add(taskModel);
		}
		return listaTareasModel;
	}
	
}
