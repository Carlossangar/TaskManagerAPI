package com.example.TaskManagerAPI.services;

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
	
	public boolean addTarea(TareaModel tareaModel) {
		Tarea tarea = tareaConverter.modelToEntity(tareaModel);
		if(!tareaRepository.existsById(tarea.getId())) {
			tareaRepository.save(tarea);
			return true;
		}
		return false;
	}
}
