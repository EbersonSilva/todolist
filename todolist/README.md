# todolist

## O projeto tem como objetivo simular o gerenciamento de tarefas usando Java e Spring boot

- Projeto desenvolvido no mini curso de java da [Rocketseat](https://www.rocketseat.com.br/?utm_source=google&utm_medium=cpc&utm_campaign=lead&utm_term=perpetuo&utm_content=institucional-lead-home-texto-lead-brandkws-none-none-institucional-none-none-br-google&utm_term=rocketseat&utm_campaign=PROGRAMAS-ALL-BRANDKWS-SEM&utm_source=adwords&utm_medium=cpc&hsa_acc=8545075154&hsa_cam=16048648686&hsa_grp=135825188594&hsa_ad=579096962131&hsa_src=g&hsa_tgt=kwd-679159515078&hsa_kw=rocketseat&hsa_mt=b&hsa_net=adwords&hsa_ver=3&gad_source=1&gclid=Cj0KCQjwh7K1BhCZARIsAKOrVqFjtvqEzc7PpW7-zqWJPHmOiRHv7dyr1Lq-XdBbZHqQIxYPy_YmbHUaAiqcEALw_wcB).

- Foram utilizados nesse projeto o [Maven](https://maven.apache.org/), [spring boot](https://spring.io/projects/spring-boot), [Insomnia](https://insomnia.rest/download) e [Java 17](https://www.oracle.com/br/java/technologies/downloads/).

- Tambem utilizamos o [H2 Database]( https://www.h2database.com/html/main.html) para fazer o banco de dados temporário para teste.

- Usado tambem o [Lombok](https://projectlombok.org/) para evitar a poluição de metodos.

- Utilizei o [LIB Favre bcrypt](https://github.com/patrickfav/bcrypt.) para criptografia de senha do usuário ao salvar no banco de dados. 

- [Clique aqui](http://localhost:8080/h2-console) -> Endereço para visualização das tabelas criadas do todolist enquanto o programa estiver rodando.

- Utilizado uma dependencia do spring boot [Developer tools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html) para fazer um live reload no desenvolvimento da aplicação.

- [Render.com](https://render.com/) para fazer a hospedagem do nosso projeto.

## Quais as funcionalidades da aplicação? 

- A aplicação faz  cadastro de usuarios e tarefas.

- Validação de data de inicio e termino das tarefas. Não sendo possivel a criação de tarefas com datas anteriores a atual, e nem com data de inicio após a data de término.

- Alteração de tarefas parcialmente, sem interferir nos demais campos.

- Validação para que somente o usuário que criou a tarefa tenha autorização para altera-la.

- Requisição de todas as tarefas criadas de determinado usuario pelo numero do ID do mesmo.

- Validação caso não exista tarefa do usuário.

- Criado uma Exception para tratamento de erro ao salvar mais de 50 caracteres no campo de 'title'.



