package br.com.ebersonsilva.todolist.task;

import java.util.List;
import java.util.UUID;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    //Tras uma lista de tarefas de um determinado usuario.
    List<TaskModel> findByIdUser(UUID idUser); 
}
