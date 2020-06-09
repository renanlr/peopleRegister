#PeopleRegister

### Pré-requisitos
- Mysql
- Wildfly server 19
- Java 8 ou superior
- Maven
- mysql-connector-java

#### Criar Base de Dados e User Mysql
Digite os seguintes comandos no mysql
```
CREATE USER 'people-user'@'localhost' IDENTIFIED BY 'people-user';
GRANT ALL PRIVILEGES ON * . * TO 'people-user'@'localhost';
CREATE DATABASE peopleregisterdb;
```

#### Criar Datasource Wildfly console
Digite os seugintes comando no widfly
```
module add --name=com.mysql --resources=/path/to/your/mysql-connector-java-8.0.20.jar --dependencies=javax.api,javax.transaction.api
/subsystem=datasources/jdbc-driver=mysql:add(driver-name=mysql,driver-module-name=com.mysql,driver-xa-datasource-class-name=com.mysql.cj.jdbc.MysqlXADataSource)
data-source add --name=PeopleRegisterDS --jndi-name=java:jboss/datasources/PeopleRegisterDS --driver-name=mysql  --connection-url=jdbc:mysql://localhost:3306/peopleregisterdb --user-name=people-user --password=people-user --min-pool-size=10 --max-pool-size=20
```

#### Rodando a aplicação 
Após ter o ambiente configurado basta seguir os seguintes passos
- Iniciar o servidor wildfly, com o projeto publicado no mesmo
- acessar a url localhost:8080 