package br.com.ebersonsilva.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
// Definição de todos os getters e setters com Lombok
import lombok.Data;
@Data
@Entity(name = "tb_users")

public class UserModel {
  @Id
  @GeneratedValue(generator = "UUID")

  private UUID id;
  @Column(unique = true)
  private String username;
  private String name;
  private String password;


  @CreationTimestamp
  private LocalDateTime createdAt;

}
