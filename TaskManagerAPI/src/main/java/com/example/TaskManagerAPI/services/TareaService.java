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
	
	public ArrayList<TareaModel> getTareas(){
		List<Tarea> tareas = tareaRepository.findAll();
		ArrayList<TareaModel> listaTareasModel = new ArrayList<>();
		for (Tarea task : tareas) {
			TareaModel taskModel = tareaConverter.entityToModel(task);
			listaTareasModel.add(taskModel);
		}
		return listaTareasModel;
	}
	
	public ArrayList<TareaModel> getTaskByStatus(String state){
		List<Tarea> tareas = tareaRepository.findByEstado(state);
		ArrayList<TareaModel> tareasModel = new ArrayList<>();
		for (Tarea tarea : tareas) {
			tareasModel.add(tareaConverter.entityToModel(tarea));
		}
		return tareasModel;
	}
	
	public TareaModel getTarea(long id) {
		TareaModel tareaSolicitada = new TareaModel();
		//Extraemos un optional de la base de datos
		Optional<Tarea> optionalTarea = tareaRepository.findById(id);
		//Comprobamos si existe la tarea
		if(optionalTarea.isPresent()) {
			//En caso de que si, devolvemos el usuario.
			return tareaConverter.entityToModel(optionalTarea.get());
		}
		//En caso de que no, devolvemos un null
		return null;
	}
	
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
	
	public void actualizarTarea(TareaModel tareaModel) {
		tareaRepository.save(tareaConverter.modelToEntity(tareaModel));
	}
	
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
