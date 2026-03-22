1. INTRODUCCIÓN

1.1 Contexto General
La empresa de transporte intermunicipal TransCesar S.A.S. identificó la necesidad de modernizar su proceso de venta de tickets. El manejo manual de las operaciones generaba errores en el registro de pasajeros, pérdida de información y dificultades para consultar las ventas del día. Para resolver esta problemática, se contrató un equipo de desarrollo con la misión de construir un sistema de consola aplicando los principios de Programación Orientada a Objetos (POO) y Arquitectura en Capas.

1.2 Justificación
El sistema nació del Taller 2 como una solución base que cubre la gestión de vehículos, conductores, pasajeros y la venta de tickets. Posteriormente, el Parcial del Primer Corte extendió el sistema con un módulo crítico de reservas, respondiendo a otra necesidad identificada por la empresa: los pasajeros debían presentarse físicamente en la terminal el mismo día de viaje sin garantía de encontrar cupos disponibles, lo que generaba pérdida de clientes y mala experiencia de servicio.

1.3 Objetivos
•	Implementar el sistema de gestión de tickets de TransCesar S.A.S. bajo una arquitectura en capas (Model, DAO, Service, View).
•	Aplicar correctamente los principios de POO: herencia, polimorfismo, clases abstractas e interfaces.
•	Garantizar la persistencia de datos mediante archivos de texto plano con separador punto y coma (;).
•	Extender el sistema con un módulo de reservas que permita a los pasajeros apartar cupos con anticipación.
•	Demostrar trabajo colaborativo mediante el uso de Git y ramas separadas por integrante.

 
2. ARQUITECTURA EN CAPAS

El sistema está organizado en cuatro capas según los principios de arquitectura en capas. La comunicación fluye únicamente en una dirección: View → Service → DAO → Model. Ninguna capa puede saltarse a otra, lo que garantiza bajo acoplamiento y alta cohesión.

Capa	Paquete	Responsabilidad
Vista (View)	trans_cesar.view	Interfaz de consola con la que interactúa el usuario. Contiene los menús: Menu principal, MenuVehiculo, MenuConductor, MenuPasajero, MenuTicket, MenuReportes y MenuReservas.
Servicio (Service)	trans_cesar.service	Aplica las reglas del negocio. Valida cupos, licencias, placas duplicadas, límite de tickets por día, tarifas en festivos y todas las reglas de reservas.
Acceso a datos (DAO)	trans_cesar.dao	Lee y escribe en los archivos de texto plano (.txt). Cada entidad tiene su propio DAO: VehiculoDAO, ConductorDAO, PasajeroDAO, TicketDAO, ReservaDAO.
Modelo (Model)	trans_cesar.model	Clases que representan las entidades del negocio: Vehiculo, Buseta, MicroBus, Bus, Persona, Conductor, Pasajero, PasajeroRegular, PasajeroEstudiante, PasajeroAdultoMayor, Ticket, Reserva.

2.1 Capa Model
Contiene las entidades del dominio. Se definen dos jerarquías de clases abstractas:
•	Vehiculo (abstracta): atributos placa, ruta, capacidadMaxima, pasajerosActuales. Subclases: Buseta (19 pax, $8.000), MicroBus (25 pax, $10.000), Bus (45 pax, $15.000).
•	Persona (abstracta): atributos cedula y nombre. Subclases: Conductor (licencia, categoría B1/B2/C1/C2) y Pasajero (abstracta con calcularDescuento()). Sub-subclases: PasajeroRegular (0%), PasajeroEstudiante (15%), PasajeroAdultoMayor (30%).
•	Ticket: registra pasajero, vehículo, fecha de compra, origen, destino y valor final con descuento aplicado.
•	Reserva (nueva entidad del parcial): código, pasajero, vehículo, fechaCreacion, fechaViaje y estado (Activa, Convertida, Cancelada).

2.2 Interfaces del Sistema
•	Imprimible: define imprimirDetalle(). Implementada por todas las clases del modelo para mostrarse en consola de forma legible.
•	Calculable: define calcularTotal(). Implementada por Ticket (retorna valor final con descuento) y por los reportes estadísticos (retorna total recaudado).

2.3 Polimorfismo en el Cálculo de Descuentos
El descuento por tipo de pasajero se resuelve mediante polimorfismo. La clase abstracta Pasajero define calcularDescuento() y cada subclase lo implementa con su comportamiento específico. Cuando el sistema calcula el valor de un ticket, llama pasajero.calcularDescuento() sin necesidad de preguntar qué tipo de pasajero es, lo que hace el código extensible y limpio.

2.4 Capa DAO – Persistencia
Cada entidad tiene su propio archivo de persistencia y clase DAO correspondiente:
•	buseta.txt, bus.txt, microbus.txt → VehiculoDAO (y subclases)
•	conductores.txt → ConductorDAO
•	pasajeros.txt → PasajeroDAO
•	tickets.txt → TicketDAO
•	reservas.txt → ReservaDAO (nuevo en el parcial)
Cada registro ocupa una línea y los campos se separan con punto y coma (;). Al iniciar el sistema, todos los archivos se cargan en memoria automáticamente.

2.5 Capa Service – Reglas de Negocio
Todas las validaciones residen exclusivamente en la capa Service:
•	Verificar que el vehículo tenga cupos antes de vender un ticket.
•	Verificar que el conductor tenga licencia antes de asignarlo a un vehículo.
•	Verificar que no se registren dos vehículos con la misma placa.
•	Validar que un pasajero no tenga más de 3 tickets en el mismo día.
•	Adicionar 20% a la tarifa en días festivos.
•	Validar todas las reglas del módulo de reservas (ver sección 4).

2.6 Capa View – Interfaz de Consola
Contiene los menús de consola con los que interactúa el usuario. Cada entidad tiene su propio menú: MenuVehiculo, MenuConductor, MenuPasajero, MenuTicket, MenuReportes y el nuevo MenuReservas. El Menú principal integra todas las opciones.

 
3. DIAGRAMA DE CLASES

A continuación se describe la estructura completa de clases del sistema. El diagrama refleja las relaciones de herencia, implementación de interfaces, composición y asociación entre todas las entidades.

3.1 Jerarquía de Vehículos
<<abstracta>> Vehiculo
  - placa: String
  - ruta: String
  - capacidadMaxima: int
  - pasajerosActuales: int
  - disponible: boolean
  + getTarifa(): double  [abstracto]
  + imprimirDetalle()  [de Imprimible]

Buseta extends Vehiculo  implements Imprimible
  capacidadMaxima = 19  |  tarifaBase = 8000

MicroBus extends Vehiculo  implements Imprimible
  capacidadMaxima = 25  |  tarifaBase = 10000

Bus extends Vehiculo  implements Imprimible
  capacidadMaxima = 45  |  tarifaBase = 15000

3.2 Jerarquía de Personas
<<abstracta>> Persona
  - cedula: String
  - nombre: String

Conductor extends Persona  implements Imprimible
  - numeroLicencia: String
  - categoriaLicencia: String  // B1, B2, C1, C2

<<abstracta>> Pasajero extends Persona  implements Imprimible
  + calcularDescuento(): double  [abstracto]

PasajeroRegular extends Pasajero   → calcularDescuento() = 0.0
PasajeroEstudiante extends Pasajero → calcularDescuento() = 0.15
PasajeroAdultoMayor extends Pasajero → calcularDescuento() = 0.30

3.3 Clase Ticket
Ticket  implements Imprimible, Calculable
  - id: String
  - pasajero: Pasajero
  - vehiculo: Vehiculo
  - fechaCompra: LocalDate
  - origen: String
  - destino: String
  - valorFinal: double
  + calcularTotal(): double   // tarifa * (1 - descuento) * (1 + 0.2 si festivo)

3.4 Clase Reserva (Módulo del Parcial)
Reserva  implements Imprimible
  - codigo: String                     // Código único
  - pasajero: Pasajero
  - vehiculo: Vehiculo
  - fechaCreacion: LocalDateTime       // Para calcular expiración 24h
  - fechaViaje: LocalDate
  - estado: String                     // ACTIVA | CONVERTIDA | CANCELADA
  + estaVencida(): boolean             // > 24h desde fechaCreacion
  + imprimirDetalle()

 
4. MÓDULO DE RESERVAS (PARCIAL PRIMER CORTE)

El módulo de reservas es el componente principal del Parcial. Permite a los pasajeros apartar un cupo en un vehículo con anticipación, solucionando el problema de la asistencia presencial sin garantía de disponibilidad.

4.1 Reglas de Negocio
Regla de Negocio	Descripción
Capacidad máxima	Un vehículo no puede tener más reservas activas que su capacidad máxima, contando tickets vendidos y reservas activas simultáneamente.
Expiración de 24 horas	Una reserva se considera vencida cuando han transcurrido más de 24 horas desde su creación sin haber sido convertida en ticket.
Reserva única por viaje	Un pasajero no puede tener más de una reserva activa para el mismo vehículo en la misma fecha de viaje.
Conversión con reglas normales	Al convertir una reserva en ticket se aplican todas las reglas de venta: descuento por tipo de pasajero (Regular 0%, Estudiante 15%, Adulto Mayor 30%) y tarifa por festivo (+20%) si aplica.
Estados posibles	Activa: cupo apartado y contado en capacidad. Convertida: ticket generado, cupo liberado del conteo de reservas. Cancelada: cupo liberado para nuevas reservas o compras directas.

4.2 Persistencia
Las reservas se guardan en el archivo reservas.txt usando el mismo formato del sistema: un registro por línea con campos separados por punto y coma (;). Al iniciar el sistema, se cargan todas las reservas en memoria y se verifican automáticamente cuáles han expirado (más de 24 horas de antigüedad) para actualizar su estado a Cancelada.

4.3 Menú de Reservas (Capa View)
El menú principal incluye una opción de Reservas que despliega el siguiente submenú:
1.	Crear una nueva reserva
2.	Cancelar una reserva existente por su código
3.	Listar todas las reservas activas
4.	Listar historial completo de reservas de un pasajero
5.	Convertir una reserva en ticket (aplica reglas normales de venta)
6.	Verificar reservas vencidas (cancela automáticamente las que superen 24h e informa cuántas fueron canceladas)

 
5. PLAN DE TRABAJO Y EVIDENCIA DE COMMITS
5.1 Distribución de Roles
Líder de Desarrollo (rama: lider)
Responsable de la coordinación general del proyecto y la correcta aplicación de la arquitectura en capas.
•	Administrar el repositorio en GitHub y la rama lider 
•	Crear y organizar la estructura del proyecto (model, dao, service, view) 
•	Definir interfaces globales (Imprimible y Calculable) 
•	Desarrollar toda la capa VIEW (menús del sistema) 
•	Implementar la clase principal (main) 
•	Revisar e integrar el trabajo de los desarrolladores mediante merge 
•	Garantizar que se cumpla el flujo: view → service → dao → model 
•	En el parcial: coordinar la integración completa del sistema, incluyendo el módulo de reservas 
Desarrollador 1 (rama: desarrollador1)
Responsable del módulo de Vehículos y Reservas.
•	Implementar la jerarquía de vehículos: 
o	Vehiculo (abstracta) 
o	Bus, Buseta, MicroBus 
•	Desarrollar: 
o	VehiculoDAO 
o	VehiculoService 
•	Implementar atributos: 
o	placa, capacidad, cupos, ruta, estado 
•	Aplicar interfaz Imprimible 
 En el parcial:
•	Implementó el módulo completo de reservas: 
o	Reserva 
o	ReservaDAO 
o	ReservaService 
(Esto está BIEN asignado a este desarrollador, lo dejaste correcto)
👤 Desarrollador 2 (rama: desarrollador2)
Responsable del módulo de Personas, Tickets y Reportes.
•	Implementar la jerarquía de personas: 
o	Persona (abstracta) 
o	Conductor 
o	Pasajero (abstracta) 
o	PasajeroRegular, PasajeroEstudiante, PasajeroAdultoMayor 
•	Implementar: 
o	Ticket 
•	Desarrollar: 
o	ConductorDAO, PasajeroDAO, TicketDAO 
o	ConductorService, PasajeroService, TicketService 
•	Aplicar: 
o	Imprimible 
o	Calculable (especialmente en Ticket) 
•	Implementar lógica de: 
o	descuentos (polimorfismo) 
En el parcial:
•	Desarrolló el módulo de reportes: 
o	MenuReportes 
o	estadísticas: 
	total de ventas 
	pasajeros por tipo 
FLUJO GENERAL DEL PROYECTO
 
FLUJO
 
 
 
5.2 Historial de Commits por Integrante
Rama / Integrante	Commits relevantes	Funcionalidad
Líder (lider)	18a7d1c – merge listo
e883bbb – Merge desarrollador2
54f343c – fix: resolver conflicto menuRutas
307a1fb – menu terminado	Integración de ramas, capa View principal, MenuRutas, resolución de conflictos de merge.
Desarrollador 1 (desarrollador1)	bd87f4b – guardar reserva
3a1622e – corrección agregación reservas
7eb1670 – métodos auxiliares listado
9d7d6f7 – listado de datos de reserva
1955b48 – cancelación de reserva	Jerarquía de Vehículo, DAO y Service de vehículos, módulo completo de Reservas (model, dao, service).
Desarrollador 2 (desarrollador2)	b22ef55 – Métodos MenuReporte
d4db22d – merge completos
f93494f – MenuReportes
c703e76 – resumenPorDia
1c966ff – consultarPorTipoPasajero
60cb991 – Validar cupo vehículo
ccfadfd – TicketService
d0557bc – ConductorService	Jerarquía de Persona, clase Ticket, DAO y Service de conductores/pasajeros/tickets, MenuReportes, validaciones de negocio en TicketService.

5.3 Flujo de Integración Git
El equipo siguió las convenciones de Conventional Commits. El flujo de trabajo fue el siguiente:
•	Cada integrante trabajó en su rama individual (lider, desarrollador1, desarrollador2).
•	Las funcionalidades se commitearon de forma incremental por cada método o clase completada.
•	El líder integró el trabajo mediante pull de ramas y resolución de conflictos de merge.
•	Commit inicial común: 3840730 (primer commit) compartido por todos los integrantes.
•	El repositorio está disponible en: https://github.com/jmfigueroa366/Trancesar
•	
Estructura
trans_cesar/
│
├── src/
│ └── trans_cesar/
│
│ ├── model/
│ │ ├── Persona.java
│ │ ├── Conductor.java
│ │ ├── Pasajero.java
│ │ ├── PasajeroRegular.java
│ │ ├── PasajeroEstudiante.java
│ │ ├── PasajeroAdultoMayor.java
│ │ ├── Vehiculo.java
│ │ ├── Bus.java
│ │ ├── Buseta.java
│ │ ├── MicroBus.java
│ │ ├── Ticket.java
│ │ ├── Reserva.java
│ │ ├── Ruta.java
│ │ ├── Imprimible.java
│ │ ├── Calculable.java
│
│ ├── dao/
│ │ ├── ConductorDAO.java
│ │ ├── PasajeroDAO.java
│ │ ├── VehiculoDAO.java
│ │ ├── TicketDAO.java
│ │ ├── ReservaDAO.java
│ │ ├── RutaDAO.java
│
│ ├── service/
│ │ ├── ConductorService.java
│ │ ├── PasajeroService.java
│ │ ├── VehiculoService.java
│ │ ├── TicketService.java
│ │ ├── ReservaService.java
│ │ ├── RutaService.java
│
│ ├── view/
│ │ ├── MenuPrincipal.java
│ │ ├── MenuVehiculo.java
│ │ ├── MenuPasajero.java
│ │ ├── MenuConductor.java
│ │ ├── MenuTicket.java
│ │ ├── MenuReserva.java
│ │ ├── MenuReportes.java
│
│ └── Main.java
│
├── data/
│ ├── vehiculos.txt
│ ├── conductores.txt
│ ├── pasajeros.txt
│ ├── tickets.txt
│ ├── reservas.txt
│ ├── rutas.txt
│
└── README.md

CLASES DEL MODELO

 

VEHÍCULOS (HERENCIA)

 




TICKET Y RELACIONES

 

CLASES

Ruta
- origen
- destino
+ imprimirDetalle()

Reserva
- fecha
+ imprimirDetalle()

INTERFACES

 

RELACIONES 

 

VIEW

•	MenuConductor
MenuPasajero
MenuTicket
MenuRutas
MenuVehiculo
MenuReportes
•	Menúreservas

SERVICE

•	ConductorService
PasajeroService
TicketService
RutaService
VehiculoService

DAO

•	ConductorDAO
PasajeroDAO
ReservaDAO
RutaDAO
TicketDAO
VehiculoDAO
	
Explicación

El proyecto que desarrollamos es un sistema de gestión de tickets para la empresa de transporte intermunicipal TransCesar S.A.S., construido en Java aplicando Programación Orientada a Objetos y Arquitectura en Capas.

 El sistema se organiza en cuatro capas que se comunican en una sola dirección: la capa View contiene los menús de consola con los que interactúa el usuario, la capa Service aplica todas las reglas del negocio como validar cupos, licencias y placas duplicadas, la capa DAO lee y escribe los datos en archivos de texto plano separados por punto y coma, y la capa Model contiene las clases que representan las entidades del mundo real. En cuanto a la herencia, definimos dos jerarquías de clases abstractas: Vehiculo, de la cual heredan Buseta con capacidad para 19 pasajeros y tarifa de $8.000, MicroBus con 25 pasajeros y $10.000, y Bus con 45 pasajeros y $15.000; y Persona, de la cual hereda Conductor, que tiene número y categoría de licencia, y Pasajero, que es también abstracta y de ella heredan PasajeroRegular sin descuento, PasajeroEstudiante con 15% de descuento y PasajeroAdultoMayor con 30% de descuento. El polimorfismo se aplica en el método calcularDescuento(), que el sistema llama sobre cualquier pasajero sin preguntar qué tipo es, y Java en tiempo de ejecución ejecuta la versión correcta según la subclase. Las dos interfaces del sistema son Imprimible, que implementan todas las clases del modelo para mostrarse en consola, y Calculable, que implementan las clases con operaciones monetarias como Ticket para retornar el valor final con descuento. El aporte principal del parcial fue el módulo de Reservas, que permite a los pasajeros apartar un cupo con anticipación sin tener que presentarse físicamente el día del viaje: una reserva nace en estado Activa y cuenta dentro de la capacidad del vehículo, si el pasajero se presenta a pagar dentro de las 24 horas se convierte en ticket aplicando todos los descuentos y tarifas de festivo normales, y si no lo hace el sistema la cancela automáticamente liberando el cupo. El trabajo se distribuyó entre tres integrantes usando Git con ramas separadas: el líder integró el trabajo de todos y construyó la capa View, el Desarrollador 1 implementó la jerarquía de Vehículo y el módulo completo de Reservas, y el Desarrollador 2 implementó la jerarquía de Persona, la clase Ticket y el módulo de reportes y estadísticas, todos siguiendo las convenciones de Conventional Commits para documentar cada funcionalidad completada.

6. CONCLUSIONES

El desarrollo del sistema de gestión de tickets de TransCesar S.A.S. permitió al equipo aplicar de manera integral los conceptos de Programación Orientada a Objetos y Arquitectura en Capas estudiados en la asignatura. La separación estricta de responsabilidades entre las capas Model, DAO, Service y View facilitó el desarrollo paralelo por parte de los tres integrantes sin generar interferencias significativas en el código.

El uso de herencia y clases abstractas en las jerarquías de Vehiculo y Persona permitió reutilizar código y garantizar un diseño extensible. El polimorfismo aplicado en el cálculo de descuentos por tipo de pasajero demostró ser una solución elegante que elimina la necesidad de condicionales basados en tipo.

El módulo de reservas implementado en el parcial representó el reto más complejo, al requerir la coordinación entre reglas de negocio (capacidad, expiración de 24 horas, reserva única por viaje), persistencia en archivo plano y la conversión de una reserva en ticket respetando todas las reglas de venta normales. El equipo logró implementarlo de forma completa y funcional.

El trabajo colaborativo con Git, mediante ramas separadas y commits descriptivos siguiendo las convenciones de Conventional Commits, evidenció la participación activa de los tres integrantes y facilitó la integración final del sistema por parte del líder.
