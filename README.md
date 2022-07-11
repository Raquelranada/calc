# Calculadora básica

Microservicio que expone un API para realizar operaciones aritméticas (por ahora suma resta)

## Ejecución de los tests y reporte
Se ha incluído un plugin para generar un reporte de cobertura de los test. 

* Comando para la ejecución de los test y generación del reporte
```
mvn test
```
* Dónde encontrar el reporte (path dentro del proyecto)
```
target/site/jacoco/index.html
```

## Generación del jar

* Comando para la generación de jar + javadoc 
```
mvn clean package -DskipTests
```

* Comando para la generación de jar de fuentes
```
mvn source:jar 
```

* Dónde encontrar los jar (path dentro del proyecto)
```
target/calculadora-0.0.1-SNAPSHOT.jar
target/calculadora-0.0.1-SNAPSHOT-javadoc.jar
target/calculadora-0.0.1-SNAPSHOT-sources.jar
```

## Ejecución de la aplicación 
```
java -jar target/alculadora-0.0.1-SNAPSHOT.jar
```

## API
Para probar la llamada a los métodos (sumar/restar) se puede hacer uso del UI que publica Swagger:

http://localhost:8080/swagger-ui/index.html

Como la aplicación usa autenticación básica, es necesario hacer login con las credenciales:
```
user: test
pass: pass
```

### Sumar
![imagen](https://user-images.githubusercontent.com/93298862/178325686-0b4d6b38-43d5-4983-8f62-56237e8dffde.png)

También se puede hacer la llamada al endpoint usando curl:
```
curl -u test:pass -X POST http://localhost:8080/calculadora/sumar -H 'Content-Type: application/json' -d '[2.3,3.5,1]'

```

La respuesta obtenida es un objecto json:
```
{
   "operador":"SUMA",
   "operandos":[
      2.3,
      3.5,
      1.0
   ],
   "resultado":6.8
}
```

### Restar

![imagen](https://user-images.githubusercontent.com/93298862/178325745-8cacd463-c15c-4443-83b9-f08b8720bce5.png)

También se puede hacer la llamada al endpoint usando curl:
```
curl -u test:pass -X POST http://localhost:8080/calculadora/restar -H 'Content-Type: application/json' -d '[18,3.5,1]'

```

La respuesta obtenida es un objecto json:
```
{
   "operador":"RESTA",
   "operandos":[
      18.0,
      3.5,
      1.0
   ],
   "resultado":13.5
}esultado":6.8
}
```
