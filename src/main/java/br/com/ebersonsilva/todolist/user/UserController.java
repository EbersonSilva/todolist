package br.com.ebersonsilva.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Biblioteca de hash para criptografar o campo de senha do usuario.
import at.favre.lib.crypto.bcrypt.BCrypt;

/*
 * Modificador
 * public 
 * private
 * protected
 */
@RestController
@RequestMapping("/users") //

public class UserController {

  @Autowired 
  private IUserRepository userRepository;
// ResponseEntity para fazer a validação de dados, se ja existe na tabela ou não.
  @PostMapping("/")
  public ResponseEntity create(@RequestBody UserModel userModel) {
    var user = this.userRepository.findByUsername(userModel.getUsername());
    // Verificação para verificar se existe um username com o mesmo nome.
    if(user != null){
      // Mensagem de erro
      // Status code 400
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario já existe");
    }

    // Atribuição da criptografia de senha para a variavel passwordHashred.
    var passwordHashred = BCrypt.withDefaults()
    .hashToString(12,userModel.getPassword().toCharArray());

    userModel.setPassword(passwordHashred);

    var userCreated = this.userRepository.save(userModel);
    return ResponseEntity.status(HttpStatus.OK).body(userCreated);//status code 201 Created
  }

}
