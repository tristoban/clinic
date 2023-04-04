# Dental Clinic Appointment Booking System (APIRest)

This project aims to implement a system for managing appointment bookings for a dental clinic. The system must fulfill the following requirements:

## Requirements

- **Dentist management**: List, add, modify, and delete dentists. Record their last name, first name, and registration number.
- **Patient management**: List, add, modify, and delete patients. Store their first name, last name, address, ID, and registration date.
- **Appointment scheduling**: Assign an appointment to a patient with a specific dentist at a specific date and time.
- **Login**: Validate access to the system through a login with a username and password. Any logged-in user (ROLE_USER) must be able to book an appointment, but only those with an admin role (ROLE_ADMIN) can manage dentists and patients. A user can have only one role, and the roles will be entered directly into the database.

## Technical Requirements

The application must be developed in layers:

### Business entities layer
These are the Java classes representing our business objects modeled through the object-oriented paradigm.

### Data access layer (Repository)
These are the classes responsible for accessing the database.

### Data layer (database)
This is the database of our system, modeled through an entity-relationship model. We will use the H2 database for its convenience.

### Business layer
These are the service classes responsible for decoupling data access from the view.

### Presentation layer
These are the web pages that need to be developed using the Spring Boot MVC framework with controllers and one of these two options for the view: HTML+JavaScript or React.

It's essential to handle exceptions by logging any exception that may occur and performing unit tests to ensure the quality of the developments.

This project is developed using the Spring framework and related technologies.

## H2 Database Needed!!

Remember this configuration will need the H2 database installed in your system:




# Tutorial completo español:

A lo largo del siguiente tutorial, voy a ir explicando el por qué de la mayoría de archivos, 
lo ideal es que esta documentación no solo le sirva a la persona que me esté leyendo, sino a mi también.
Por experiencia sé que me olvido algunas cosas en el largo camino de aprender programación en general.


#### Este repo está compuesto por distintos paquetes que pueden ir variando pero la base es la misma, a continuación voy a ir explicandolos:

## "Entities"

En un proyecto de Spring las entities son objetos que representan datos de la aplicación.
Estos objetos pueden ser mapeados a una tabla de la base de datos, lo que permite que la información 
se pueda almacenar y recuperar de manera persistente.

Las entities en Spring a menudo se definen como clases Java que contienen campos y métodos que representan los 
datos de la aplicación. Cada field en una entity representa una columna en una tabla de la base de datos, y cada
instance de una entity representa una fila en esa tabla.

En el caso de este proyecto cuento con las siguientes entities:

#### Address
Address es la única clase "distinta" al resto aunque todas tengan sus diferencias obviamente. y es que tiene la anotación
"@Embeddable" la cual explico en el glosario de anotaciones sección 1 (entidades)
#### Dentist
Dentist es una clase común la cual determina la creación con sus atributos, setters, gettters y constructores para un dentista
#### Meet
La entidad meet es la mas "compleja" de todas la cual representa una cita entre un paciente y un dentista y se
mapea a una tabla correspondiente en la base de datos. La entidad Meet utiliza anotaciones de Spring para especificar 
detalles sobre cómo se deben mapear los fields de la clase a las columnas de la tabla correspondiente en la base de
datos y cómo se establecen las relaciones entre las entidades.
#### Patient
Patient es una clase común tal como Dentist


### Habiendo explicado las entidades paso a detallar las anotaciones

## Glosario de anotaciones (sección 1 - entidades)

@Entity: Esta anotación indica que la clase "X" es una entidad de la base de datos y que debe ser 
mapeada a una tabla correspondiente en la base de datos.

### Anotaciones lomBok

@Getter: Esta anotación genera automáticamente los métodos getter para todos los campos de la clase, 
incluyendo los fields de la entidad "X".

@Setter: Esta anotación genera automáticamente los métodos setter para todos los campos de la clase, 
incluyendo los fields de la entidad "X".

@NoArgsConstructor: Esta anotación genera un constructor sin argumentos para la clase "X".
Este constructor es utilizado por el framework de Spring para crear instancias de la clase "X".

@AllArgsConstructor: Esta anotación genera un constructor con argumentos para la clase "X". 
El constructor generado por esta anotación incluirá todos los fields de la clase "X" como argumentos.

### Fin de anotaciones lombok

@Table(name="x"): Esta anotación se utiliza para especificar el nombre de la tabla de la base de datos a 
la que se mapea la entidad x.

@Id: Esta anotación indica que el field que se encuentra debajo de ella es la clave primaria de la entidad x.

@GeneratedValue(strategy = GenerationType.IDENTITY): Esta anotación se utiliza para indicar que el valor de la 
clave primaria es generado automáticamente por la base de datos utilizando el tipo de estrategia de identidad.

@Column(nullable = false): Esta anotación se utiliza para especificar que el field que se encuentra debajo de 
ella debe ser mapeado a una columna de la tabla correspondiente en la base de datos, y que no se permite valores 
nulos para este field.


### Las 3 siguientes anotaciones son un ejemplo de una relación "Muchos a uno"

La anotación @ManyToOne debería ir seguida de @JoinColumn aclarando la relación de muchos a uno y repetí a propósito la
tabla @JoinColumn porque en el caso de ser una tabla intermedia como lo es "Meet" requiere tanto id de Patient como
id de Dentist...

@ManyToOne(fetch = FetchType.LAZY): Esta anotación se utiliza para especificar que hay una relación "muchos-a-uno"
entre dos entidades. En este caso, se especifica que la carga de los objetos relacionados se hace de manera perezosa 
(lazy), es decir, no se cargan hasta que se requieren.

@JoinColumn(name = "x_id", nullable = false): Esta anotación se utiliza para indicar que la columna de la tabla
correspondiente en la base de datos que se utiliza para mapear la relación "muchos-a-uno" con la entidad "x" se
llama "x_id" y que no se permite valores nulos.

@JoinColumn(name = "y_id", nullable = false): Esta anotación se utiliza para indicar que la columna de la tabla
correspondiente en la base de datos que se utiliza para mapear la relación "muchos-a-uno" con la entidad "y" se 
llama "y_id" y que no se permite valores nulos.

Por eso es que en el primer @JoinColumn el name es "x_id" y en el segundo "y_id" para dar a entender como debería
ser...


Por otro lado existe la contraparte necesaria, la anotación:

@OneToMany(mappedBy = "y"): Esta anotación se utiliza para indicar que hay una relación "uno-a-muchos"
entre dos entidades. La anotación debe especificar el nombre del field en la clase relacionada que contiene 
la clave primaria de la entidad actual.

## "Repositories"

Por otro lado existen los paquetes "repositorios" con interfaces que en resumen definen una serie de métodos
predefinidos para trabajar con las entidades que se mapean a las tablas de la base de datos, en este caso
lo hacemos extendiendo cada interfaz de "JpaRepository<X, Long>" donde X es el nombre de la entidad y el tipo de 
dato Long para la clave primaria de la misma...


## Glosario de anotaciones (sección 2 - repositorios)

@Repository: Esta anotación indica que la interface XRepository es un repositorio de Spring que
maneja la persistencia de la entidad X en la base de datos. Es decir, esta anotación le indica a 
Spring que se debe utilizar esta interface como un componente de la aplicación que maneja la persistencia
de los datos de los "X's".

## "Services"

El paquete services en una aplicación de Spring generalmente contiene las clases que proporcionan la lógica
general de "negocio", en este caso el negocio es una clinica dental, donde vamos a intentar aprovechar las funcionalidades
para gestionar pacientes, dentistas y meets (consultas o turnos).

Estas clases suelen ser las responsables de interactuar con las entidades, manejar las "transacciones" (con la bdd),
realizar cálculos y validaciones, entre otras tareas...

En este proyecto definimos un repositorio de tipo repositorio como atributo único, le asignamos un constructor y 
vamos a estar utilizandolo como medio para la ida y vuelta de información.
Como este es un proyecto dentro de todo "básico" solo se tienen métodos como "existe por id" "crear x" 
"actualizar x" "buscar por id" "buscar todos" y "borrar x"...

En estas clases es donde más deberiamos pensar, porque de acá va a salir el cómo hacemos algo... con código 
directo de java.


### ¿Algo mas que se deba saber?

Dentro de los servicios vamos a estar utilizando los métodos que extendemos de JpaRepository en nuestra variable
de tipo repositorio que creamos...
Con ella vamos a comunicarnos, realizando las famosas operaciones "CRUD" (Create, Read, Update, Delete), a continuación
dejo algunas:

.save(): Este método se utiliza para guardar una entidad en la base de datos. 
Si la entidad ya existe en la base de datos, se actualiza con los nuevos valores. 
Si la entidad no existe en la base de datos, se crea una nueva.

.findById(): Este método se utiliza para buscar una entidad por su clave primaria en la base de datos. 
Si la entidad no existe en la base de datos, este método devuelve un objeto vacío.

.findAll(): Este método se utiliza para recuperar todas las entidades de una tabla de la base de datos.

.delete(): Este método se utiliza para eliminar una entidad de la base de datos. 
Se pueden eliminar entidades por su clave primaria o por un objeto de entidad.

Es necesario que estos métodos de repositorio sean llamados dentro de sus respectivos métodos en nuestra clase,
la cual tiene una lógica sencilla... pensemos siempre de atrás para adelante, si voy a eliminar mediante un id,
lo lógico es que nuestro método reciba por parámetro un id...

## Glosario de anotaciones (sección 3 - servicios)

@Service: Esta es una anotación de Spring que se utiliza para indicar que una clase es un servicio de la aplicación. 
Es decir, esta anotación le indica a Spring que la clase se utiliza para implementar la lógica de negocio de la 
aplicación. Al utilizar la anotación @Service, Spring automáticamente detecta esta clase y la registra como 
un componente de la aplicación.


## "Controllers"

Ahora que fuimos viendo el paso a paso de las capas de esta api, nos queda controladores. Estas son las clases
que se encargan de recibir y responder a las solicitudes HTTP que llegan a la aplicación. Los controladores proporcionan
una interfaz de programación de aplicaciones (API) para que los usuarios o mejor dicho, clientes puedan interactuar
con ella a través de la web. Lamentablemente a pesar de mi esfuerzo, la definición la siento un poco vaga, pero
con el siguiente glosario de anotaciones, se logra entender cual es es el propósito real.

## Glosario de anotaciones (sección 4 - controladores)

@RestController: Esta anotación se utiliza para indicar que una clase es un controlador de Spring y que se utiliza
para responder a las solicitudes HTTP que llegan a la aplicación. Los controladores anotados con @RestController
devuelven directamente objetos como respuesta, que son serializados en formato JSON automáticamente por Spring.

@RequestMapping("/api"): Esta anotación se utiliza para indicar que todas las solicitudes HTTP que llegan a la
raíz de la URL /api se manejarán en este controlador. En este caso, todas las solicitudes que lleguen a la 
URL http://localhost:8080/api se manejarán en este controlador.

@Autowired: Esta anotación se utiliza para indicar que un field en una clase debe ser inyectado con una instancia
de otra clase de Spring. Por ejemplo, se puede utilizar esta anotación para inyectar un objeto de un repositorio 
en un controlador.

@GetMapping: Esta anotación se utiliza para indicar que un método en un controlador maneja las solicitudes HTTP 
GET que llegan a la URL especificada en la anotación. Por ejemplo, @GetMapping("/dentists") indica que el método 
maneja las solicitudes GET que llegan a la URL /dentists.

@PostMapping: Esta anotación se utiliza para indicar que un método en un controlador maneja las solicitudes HTTP 
POST que llegan a la URL especificada en la anotación.

@PutMapping("/{id}"): Esta anotación se utiliza para indicar que un método en un controlador maneja las solicitudes
HTTP PUT que llegan a la URL especificada en la anotación. El parámetro /{id} indica que la URL debe incluir un 
identificador único para la entidad que se está actualizando.

@DeleteMapping("/{id}"): Esta anotación se utiliza para indicar que un método en un controlador maneja las 
solicitudes HTTP DELETE que llegan a la URL especificada en la anotación. El parámetro /{id} indica que la URL 
debe incluir un identificador único para la entidad que se está eliminando.























