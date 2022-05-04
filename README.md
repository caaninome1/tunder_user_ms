# user_ms

User_ms es un microservicio desarrollado para la gestión de usuarios usando sringboot



# Ejecución e instalación 
Para ejecutar el micro servicio inicialmente hay que generar los ejecutables, para esto inicialmente tenemos ejecutar los siguientes comandos 
 
`$mvn clean`

`$mvn install`

Posteriormente  y ubicados en la carpeta user  ya podemos crear una imagen del contenedor con el comando 

`$docker build -t "spring-user" .`

Una vez generada la imagen ya podemos ejecutarla, un comando típico para ejecutarla será el siguiente:

`$docker run --name user-spring -p 8080:8080 spring-user:latest`
