package br.com.ebersonsilva.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ebersonsilva.todolist.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create (@RequestBody TaskModel taskModel, HttpServletRequest request){
       var idUser = request.getAttribute("idUser");
        taskModel.setIdUser((UUID) idUser);

        // Validação para verificar se a data da criação da tarefa é maior que a data atual.
        var currentDate = LocalDateTime.now(); //Atribuido a data atual na variavel currentDate.
        if(currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("A data de início / data de término deve ser maior que a data atual");
        }
        // Validação para verificar se a data de inicio da tarefa é menor que a data de termino da tarefa.
        if(taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("A data de início deve ser menor que a data de término");
        }

        var task = this.taskRepository.save(taskModel);
        // Retorna que esta OK
        return ResponseEntity.status(HttpStatus.OK).body(task);
    }
    // Instrução para retorna todas as tasks do usuario
    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser((UUID) idUser);
        return tasks;
    }

    // Exemplo:" https://localhost:8080/tasks/8554576465464-gbdfgdfgsdfg-46545457454 "
    // Instrução para atualizar tasks caso necessário.
    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, HttpServletRequest request, @PathVariable UUID id){
        var task = this.taskRepository.findById(id).orElse(null);

        if ( task == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tarefa não encontrada");
        }
        
        var idUser = request.getAttribute("idUser");
        // Validação para que o usuario nao altere as tarefas de outro usuario.
        if(!task.getIdUser().equals(idUser)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("Usuário não tem permissão para alterar essa tarefa");
        }
       Utils.copyNonNullProperties(taskModel, task);
       
       var taskUpdate = this.taskRepository.save(task);
        return ResponseEntity.ok().body(taskUpdate);
    }
}
