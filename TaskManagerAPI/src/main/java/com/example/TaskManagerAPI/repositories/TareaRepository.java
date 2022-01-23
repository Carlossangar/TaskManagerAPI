package com.example.TaskManagerAPI.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.TaskManagerAPI.entities.Tarea;

@Repository
public interface TareaRepository extends JpaRepository<Tarea, Long>{

}
