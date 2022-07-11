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
mvn package -DskipTests
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
java -jar calculadora-0.0.1-SNAPSHOT.jar
```
