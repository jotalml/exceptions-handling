# Introducción exceptions-handling
Implementación del **exceptionshandling** (manejador de excepciones). Dicho componente será reutilizado por todas las APIs que se implementen bajo la tecnología Springboot.

# Como Empezar?

## 1. Proceso de Instalación local
- instalar Java: el JDK para Java 11
- instalar Maven: a partir de la version 3.*
- configurar las variables de entorno del S.O: Las variables a crear son JAVA_HOME y MAVEN_HOME, con el propósito de poder invocar los comandos de dichas tecnologías a travez de la terminal o consola.
- crear el archivo **settings.xml**: este archivo es necesario para configurar la autenticación hacia el repositorio donde se va a subir la dependencia exceptionshandling. Dicho archivo debe crearse en el directorio home de Maven (habitualmente dicho directorio .m2 se encuentra en el directorio del usuario). El contenido de dicho archivo debe ser el siguiente: <br>

## 2. Estructura
La estructura a nivel del paquete **com.jotalml.utils** es el siguiente:
- **ExceptionshandlingApplication**: clase que contiene el método de inicio **_public static void main_**
- **BadRequestException**: Clase creada para declarar excepciones concerniente a parámetros inválidos en las peticiones HTTP
- **NotFoundException**: Clase creada para declarar excepciones cuando no existe resultado alguno de la búsqueda
- **InternalException**: Clase creada para declarar excepciones que no son controladas o no se encuentran declaradas en _ApiExceptionHandler_
- **ApiExceptionHandler**: Clase Proxy encargada de capturar todas las excepciones que se declaren en la misma clase. 
- **ErrorMessage**: Clase de Respuesta para devolver el detalle de la excepción capturada y procesada por _ApiExceptionHandler_ 

## 2. Publicación
Una vez finalizado los cambios en el código, lo que resta de hacer es la publicación de la nueva versión del artefacto. Para poder llevar a cabo la publicación desde el entorno local, debe haber cumplido todo lo que se mencionó anteriormente en el **1. Proceso de Instalación local**, además de lo que se declara a continuación:
- abrir una terminal en el directorio raíz del presente repositorio git: verifique que tenga debidamente configurado Java y Maven, ejecutando el comando **mvn --version**

Ya cumplido lo anterior, desde la terminal, simplemente deberá hacer lo siguiente:
- Publicación del nuevo artefacto: **mvn deploy**

## 3. Uso de la dependencia
Después de haber realizado todo lo mencionado en **1. Proceso de Instalación local**, en el proyecto maven de la API, deberá hacer lo siguiente:
1. configurar el pom.xml para descargar la dependencia: 
    - debe agregar el tag xml **dependency**:
    ```xml 
      <dependency>
         <groupId>com.jotalml.utils</groupId>
         <artifactId>exceptionshandling</artifactId>
         <version>{version}</version>
      </dependency>
   ```
   en el valor de **_version_** debe declarar la que indique versión actual (ver 4. Latest releases)
   - debe agregar el tag **repository** en las secciones **repositories** y **distributionManagement**.
   ```xml 
     <repository>
         <id>repositoryId</id>
         <name>RepositoryName</name>
         <url>https://repository.com/repository/maven-public/</url>
         <releases>
             <enabled>true</enabled>
             <updatePolicy>never</updatePolicy>
         </releases>
         <snapshots>
             <enabled>false</enabled>
         </snapshots>
     </repository>
   ```

2. crear la clase **ApiExceptionHandlerLocal**: Dicha clase debe extender de la clase **ApiExceptionHandler** que aparece en el artefacto descargado, y emplear la anotación **@ControllerAdvice**
   ```java
    @ControllerAdvice
    public class ApiExceptionHandlerLocal extends ApiExceptionHandler {
    
    }
   ``` 

3. lanzar las excepciones capturadas por la clase **ApiExceptionHandler**

## 4. Latest releases
- 1.0.0-RELEASE: primer liberación
