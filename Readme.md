# Proyecto TEST-BACKEND

Para la ejecución del proyecto es necesario el generar el esquema ionix_db con la siguiente tabla:

```
CREATE TABLE 'user' (
  'user_id' int NOT NULL AUTO_INCREMENT,
  'name' varchar(50) DEFAULT NULL,
  'user_name' varchar(30) NOT NULL,
  'email' varchar(250) DEFAULT NULL,
  'phone' varchar(15) DEFAULT NULL,
  PRIMARY KEY ('user_id'),
  UNIQUE KEY 'user_name_UNIQUE' ('user_name'),
  UNIQUE KEY 'email_UNIQUE' ('email')
) 
```


Para empaquetar el proyecto:
mvn clean package



Para Ejecutar el proyecto:
mvn spring-boot:run


La ruta generada para swagger-ui es:
[http://localhost:8181/test-backend/swagger-ui/](http://localhost:8181/test-backend/swagger-ui/)

El informe de los test unitarios queda en la siguiente ruta:
./target/site/jacoco/index.html
