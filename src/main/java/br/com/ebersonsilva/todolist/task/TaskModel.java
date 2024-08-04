package br.com.ebersonsilva.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

// Criação da tabela de tarefas(Tasks)
@Data
@Entity(name = "tb_tasks")
public class TaskModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;

    @Column(length = 50) //Definindo tamanho de 50 caracteres no campo de title.
    private String title;
    private LocalDateTime startAt; // Data de inicio 
    private LocalDateTime endAt; // Data de fim  
    private String priority;

    private UUID idUser;

    @CreationTimestamp
    private LocalDateTime createdAt; // Grava a data de quando a tarefa foi criada
    // Exceção criada ao ultrapassar a magem de 50 caracteres no campo do title
    public void setTitle(String title)throws Exception {
        if (title.length() > 50){
            throw new Exception("O campo title deve conter no máximo 50 caracteres");
        }
        this.title = title;
    }

    
}
