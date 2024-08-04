package br.com.ebersonsilva.todolist.filter;
// Filtro para autenticar usuario para criar tarefas

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.ebersonsilva.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class FilterTaskAuth extends OncePerRequestFilter{

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                var servletPath = request.getServletPath();
                // Validação somente para a rota de "Tasks"
                if (servletPath.startsWith("/tasks/")){
                // Pegar a autenticação (usuario e senha)
               var authorization = request.getHeader("Authorization");

               // Retira o "BASIC" da autenticação e deixa somente o codigo utilizando o "substring" e o "trim" retira os espaços em branco.
               var authEncoded =  authorization.substring("Basic".length()).trim();
                // Criação de Array de bytes decodificado
               byte[] authDecode = Base64.getDecoder().decode(authEncoded);
                // Conversão da variavel codificada em String
               var authString = new String(authDecode);

            //Faz a divisão da String criada, retirando os dois pontos
            // ["EbersonSilva", "123456"]
               String[] credentials = authString.split(":"); 
               String username = credentials[0];//username inserido no indice 0 do array.
               String password = credentials[1];//password inserido no indice 1 do array.
               

                // Validar se usuario existe
                var user = this.userRepository.findByUsername(username);
                if (user == null){
                    response.sendError(401, "Usuario não tem autorização"); //mensagem de erro que o usuario não tem autorização para criar tarefa.
                } else{ 

                     // Validar senha 
                   var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                  if(passwordVerify.verified) 
                  {
                // Setado atributo na request.
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                  } 
                  else {
                    response.sendError(401, "Usuario não tem autorização");
                        }   
                // Segue viagem
            }
        } else {
            filterChain.doFilter(request, response);
        }
       
    }
        
}