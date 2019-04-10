# Spring Security - Basic Authentication

Se le agregara a un servicio restful el mecanismo Basic Authentication. El servicio que utilizaremos expondrá metodo get en la url http://localhost:7070/users/userDetail, el cual devuelve el 
siguiente json

>     { 
>          name: 'user', 
>          age:35
>     }

## Guides

### Configuración básica

#### Pre requisitos
* Saber crear un servicio restful. 

#### Dependencias necesarias
     Group: 'org.springframework.cloud' ; artifact: 'spring-cloud-starter-security'
     Group: 'org.springframework.boot' ; artifact: 'spring-boot-starter-web'

#### Procedimiento

* Configurar dependencias de librerías
* Configurar el servicio restful
* Crear servicio restful
* Configurar Basic Authentication

##### Agregar Dependencias
Para configurar las dependencia en nuestro proyecto solo tenemos que agregar en nuestro archivo gradle o maven, las siguientes las dependencias especificadas anteriormente.

   Gradle

   >     dependencies {
   >          implementation 'org.springframework.cloud:spring-cloud-starter-security'
   >          compile 'org.springframework.boot:spring-boot-starter-web'
   >     }

   Maven
   
   >     <dependencies>
   >          <dependency>
   >               <groupId>org.springframework.cloud</groupId>
   >               <artifactId>spring-cloud-starter-security</artifactId>
   >          </dependency>
   >          <dependency>
   >               <groupId>org.springframework.boot</groupId>
   >               <artifactId>spring-boot-starter-web</artifactId>
   >          </dependency>
   >     </dependencies>
 

##### Configurar el servicio restful
Esta configuración se tiene que realizar en nuestro archivo application.properties o application.yml. Por el momento no agregaremos nada sobre la configuración de ribbon

Las propiedades básicas que son conveniente configurar son:

_Configuramos el puerto del servicio_
> server.port = 7070

##### Crear servicio restful
Creamos nuestro Controller y exponemos el método getUserDetail

Ejemplo:

>     import org.springframework.stereotype.Controller;
>     import org.springframework.web.bind.annotation.GetMapping;
>     import org.springframework.web.bind.annotation.ResponseBody;
>
>     @Controller
>     public class ServiceController {
>	
>          @GetMapping("/users/userDetail")
>          @ResponseBody
>          public String getUsereDetails() {
>               return "{ name: 'user', age:35 } ";
>          }
>     }

### Configurar Basic Authentication
Al crear nuestro proyecto con Spring Initializry agregar spring cloud security, si corremos nuestra aplicación con lo expuesto hasta el momento, spring boot expondrá nuestro servicio con una configuración de seguridad Basic Authentication.
Donde el usuario es "user" y la pass se nos mostrara por consola como se ve en la siguiente imagen

>     Using generated security password: a0651ca1-2f81-45d9-b91a-cc5a225d38b0

Para personalizar la configuración lo que vamos a realizar es crear nuestra clase config, la cual habilitaremos con la anotación @EnableWebSecurity. 

Luego configuraremos nuestro usuario y pass en memoria (con el fin de no depender de ningún agente externo).

Ejemplo:

>     import org.springframework.beans.factory.annotation.Autowired;
>     import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
>     import org.springframework.security.config.annotation.web.configuration.*;
>
>     @EnableWebSecurity
>     public class WebSecurityConfig{ 
>
>          @Autowired
>          public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
>               auth.inMemoryAuthentication().withUser("user").password("{noop}password").roles("USER");
>          }	
>
>     }