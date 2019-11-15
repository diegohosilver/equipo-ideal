# equipo-ideal
### Trabajo práctico para Programación III
Este repositorio contiene código fuente y consignas a realizar en Java
### Estructura del proyecto
```
└───src
    ├───main
    │   ├───negocio -----------> Clases con la lógica de negocio del proyecto
    │   │   ├───equipo --------> Clases con la lógica para generar el equipo ideal con los requisitos correspondientes
    │   │   └───personas ------> Clases con la lógica para generar personas con un rol y establecer incompatibilidades entre ellas
    │   └───interfaz ----------> Clases con las interfaces visuales del proyecto
    │   │   └───controles -----> Clases con la lógica para generar controles
    │   └───util --------------> Clases con funciones auxiliares
    └───test
        ├───controles ---------> Tests para verificar la funcionalidad de los diferentes generadores de controles visuales
        ├───equipo ------------> Tests para verificar la funcionalidad de las distintas clases dentro de equipo
        └───personas ----------> Tests para verificar la funcionalidad de las diferentes clases dentro de personas
```
### Documentación
#### `negocio.equipo`
##### `Cantidad`
Es un `Enum` con los siguientes valores:
- `MINIMO`
- `MAXIMO`

Se utiliza como parámetro de filtro dentro de `Requisitos`.
##### `Equipo`
Es una `Clase` que guarda los siguientes datos:
- Listado de miembros del equipo ideal

Cuenta con los siguientes métodos públicos:
1. `void agregarMiembro(Miembro)`: Agrega un miembro al equipo.
    * Excepciones:
        * Tipo: `IllegalArgumentException`.
        * Mensaje: _Miembro no puede estar vacio_.
2. `boolean existeMiembro(Miembro)`: Valida si un miembro existe dentro del equipo.
3. `List<Miembro> listar()`: Devuelve la lista de miembros del equipo.
4. `List<String> obtenerIdsDelEquipo()`: Devuelve los ids de los miembros del equipo.
5. `long obtenerCantidadMiembrosEnRol(Rol)`: Devuelve la cantidad de miembros dentro del rol especificado.
6. `void vaciar()`: Limpia los miembros del equipo.     
##### `EquipoIdeal`
Es un `Singleton` que resuelve por fuerza bruta la composición del equipo ideal respetando los requisitos del mismo (si es posible). <br>
Además hereda de `Observable`, por lo tanto acepta observadores los cuales recibirán el listado de miembros del equipo conformado una vez finalizado el algoritmo. <br>
Cuenta con los siguientes método públicos:
1. `EquipoIdeal obtenerInstancia()`: Devuelve la instancia de la clase.
2. `void cargarRequisitos(Requisitos)`: Guarda los requisitos a utilizar en la resolución del algoritmo.
    * Excepciones:
        * Tipo: `IllegalArgumentException`.
        * Mensaje: `Requisitos no puede estar vacio`.
3. `Requisitos obtenerRequisitos()`: Devuelve los requisitos cargados.
4. `boolean sePuedeEjecutar()`: Valida la existencia de requisitos y miembros disponibles para ejecutar el algoritmo.
5. `boolean cumpleConRequisitos()`: Valida si la resolución del algoritmo satisface los requisitos establecidos.
6. `void ejecutarAlgoritmo()`: Ejecuta el algoritmo. **Este método solamente se usa para testing**.
7. `void ejecutarAlgoritmoEnThread()`: Inicializa un `SwingWorker` y ejecuta el algoritmo en un thread aparte.
##### `Requisitos`
Es una `Clase` que guarda los siguientes datos:
- Cantidad total máxima de personas
- Cantidad total mínima de personas
- Cantidad total máxima de Testers, Programadores, Arquitectos y Líderes
- Cantidad total mínima de Testers, Programadores, Arquitectos y Líderes

Cuenta con los siguientes métodos públicos:
1. `Requisitos(Pair<Integer, Integer>, Pair<Integer, Integer>, Pair<Integer, Integer>, Pair<Integer, Integer>)`: Constructor. Recibe pares con las cantidades mínimas y máximas por cada rol.
    * Excepciones:
        * Tipo: `IllegalArgumentException`.
        * Mensaje:
            * _Par inválido_.
            * _El mínimo no puede ser mayor al máximo_.
            * _El par no puede contener valores negativos_.
            * _El equipo no puede estar vacio_
2. `int obtenerCantidadTotal(Cantidad)`: Devuelve la cantidad total de personas.
3. `int obtenerCantidadTesters(Cantidad)`: Devuelve la cantidad de testers.
3. `int obtenerCantidadProgramadores(Cantidad)`: Devuelve la cantidad de programadores.
4. `int obtenerCantidadArquitectos(Cantidad)`: Devuelve la cantidad de arquitectos.
5. `int obtenerCantidadLideres(Cantidad)`: Devuelve la cantidad de lideres.
6. `int obtenerCantidadSegunRol(Roles, Cantidad)`: Devuelve la cantidad de personas dentro del rol.
#### `negocio.personas`
##### `GeneradorMiembros`
Es un `Singleton` que genera miembros disponibles para ser utilizados. Fue creado con el único propósito de que el usuario no tenga que cargar los usuarios a mano. <br>
Cuenta con los siguientes métodos públicos:
1. `GeneradorMiembros obtenerInstancia()`: Devuelve la instancia de la clase.
2. `void generarMiembros()`: Genera 10 miembros para cada rol y los agrega a `MiembrosDisponibles`. Los nombres de los mismos son elegidos al azar dentro de una lista de 50 nombres.
##### `Persona`
Es una `Clase` que guarda los siguientes datos:
- Id
- Nombre

Cuenta con los siguientes métodos públicos:
1. `Persona(String)`: Constructor. Recibe el nombre de la persona.
    * Excepciones:
        * Tipo: `IllegalArgumentException`.
        * Mensaje: _Argumentos inválidos para el alta de la persona_.
2. `String obtenerId()`: Devuelve el Id de la persona. El mismo es un `GUID`.
3. `String obtenerNombre()`: Devuelve el nombre de la persona.
##### `Miembro`
Es una `Clase` que hereda de `Persona`. Guarda los siguientes datos:
- Rol

Cuenta con los siguientes métodos públicos:
1. `Miembro(Roles, String)`: Constructor. Recibe el rol y el nombre de la persona.
    * Excepciones:
        * Tipo: `IllegalArgumentException`.
        * Mensaje: _Rol no puede estar vacío_.
2. `Roles obtenerRol()`: Devuelve el rol del miembro.
##### `MiembrosDisponibles`
Es un `Singleton` que guarda los miembros disponibles para ser utilizados. <br>
Además hereda de `Observable`, por lo tanto acepta observadores los cuales recibirán el listado de miembros cada vez que este se actualice. <br>
Cuenta con los siguientes métodos públicos:
1. `MiembrosDisponibles obtenerInstancia()`: Devuelve la instancia de la clase.
2. `boolean existe(Miembro)`: Valida la existencia de un miembro.
3. `boolean existe(String)`: Valida la existencia de un miembro.
4. `Miembro obtener(String)`: Obtiene un miembro a partir de su id.
    * Excepciones:
        * Tipo: `NoSuchElementException`.
        * Mensaje: _No se encontró ningún miembro con el id ce919d50-b5d7-460e-8aa0-37fa3d4d64f5_
5. `void agregar(Miembro)`: Guarda el miembro.
    * Excepciones:
        * Tipo: `IllegalArgumentException`.
        * Mensaje: _Miembro no puede estar vacio_.
6. `List<Miembro> listar()`: Devuelve la lista de miembros.
7. `void vaciar()`: Limpia los miembros guardados.
##### `MiembrosIncompatibles`
Es un `Singleton` que guarda pares de miembros incompatibles entre si. <br>
Cuenta con los siguientes métodos públicos:
1. `MiembrosIncompatibles obtenerInstancia()`: Devuelve la instancia de la clase.
2. `void agregar(String, String)`: Recibe los ids de los miembros y los guarda.
    * Excepciones:
        * Tipo: `IllegalArgumentException`.
        * Mensaje:
            * _Los ids no puede estar vacios_.
            * _Los ids no tienen un formato valido_.
        * Tipo: `NoSuchElementException`.
        * Mensaje: _No existen miembros con los ids provistos_.
3. `Map<String, String> listarIds()`: Devuelve los pares de ids de miembros guardados.
4. `List<String> listar()`: Devuelve un listado de miembros incompatibles en un formato legible para el usuario.
5. `boolean tieneIncompatible(String)`: Valida si existe algún incompatible para el id provisto.
6. `boolean yaSonIncompatibles(String, String)`: Valida si los ids ya están guardados como incompatibles.
7. `boolean esIncompatibleConAlguno(String, List<String>)`: Valida si el miembro provisto es incompatible con alguno de los miembros de la lista.
8. `void vaciar()`: Limpia los miembros incompatibles.
##### `Roles`
Es un `Enum`con los siguientes valores:
- TESTER (Tester)
- PROGRAMADOR (Programador)
- ARQUITECTO (Arquitecto)
- LIDER_PROYECTO (Lider de proyecto)

Además cada valor cuenta con un nombre legible para el usuario (valores entre paréntesis).
#### `interfaz`
##### `AgregarPersona`
Es una `Clase` que permite al usuario agregar personas indicando `Nombre` y `Rol`.
##### `ListarPersonas`
Es una `Clase` que lista los miembros disponibles con su respectivo nombre y rol. Al ser un `Observer`, observa a `MiembrosDisponibles`.
##### `EstablecerRequisitos`
Es una `Clase` que permite al usuario establecer las cantidades mínimas y máximas del equipo ideal.
##### `GenerarEquipo`
Es una `Clase` que lista el equipo ideal. Al ser un `Observer`, observa a `EquipoIdeal`.
##### `PantallaPrincipal`
Es una `Clase` que concentra todas las demás pantallas.
