//====================================================
// DBML - Energy Management Systen
//Este archivo define:
//-La estructura de la base de datos
//-Las tablas
//-las relaciones entre ellas
//
//El objetivo es representar un sistema de
//gestión de producción y consumo energético
//con auditoriía de usuario
//==============================================
/*
-----------------------------------------------
Tabla: country
Función
-Tabla maestra
-almacena los países donde opera el sistema.
-Evita duplicar nombres de países en otras tablas.
-Punto raíz del modelo geográfico.
____________________________________________
*/
Table country{
  id integer [primary key] // Identificador único del país
  name varchar [unique] //Nombre del país
}
/*
___________________________________________
Tabla: region
Función
-Representa regiones o departamnetos de un país
-Depende directamnete de la tabla country
-permite análisis geográficos más detallados
*/
Table region{
  id integer [primary key]
  name varchar
  country_id integer [not null]//FK hacia country
indexes {
  (name,country_id) [unique]//Región única por país
}
}
/*
___________________________________________
Tabla:company
-representa empresa generadoras o gestoras de energía
-cada empresa pertenece a un país
-Permite agrupar plantas por empresa.
*/
Table company{
  id integer [primary key]
  name varchar
  country_id integer [not null]//FK hacia country
indexes {
  (name, country_id) [unique]
}
}

/*
___________________________________
Tabla: energy_type
Función
-Tabla catálogo de tipos de energía.
-Ejemplo:Solar, Eólica, Hidráulica, Térmica
-Permite clasificar las plantas por tipo de energía
____________________________________________
*/
Table energy_type{
  id integer [primary key]
  name varchar [unique]
  renewable boolean //Indica si es energía renovable
}
/*
Tabla: power_plant
fUNCIÓN:
-Entidad central del sistema.
-Representa una planta generadora de energía.
-Conecta empresa, región y tipo de energía.
*/
Table power_plant{
  id integer [primary key]
  name varchar
  company_id integer [not null]//FK hacia company
  region_id integer [not null]//FK hacia region
  energy_type_id integer [not null] //FK hacia energy_type
 indexes {
  (name, company_id) [unique]
 }
}
/*
Tabla measurement_type
Función:
-Define los tipos de medición energética.
-Ejemplos: Producción,Consumo, Capacidad instalada.
-Separa el tipo de dato del valor medido
*/
table measurement_type{
  id integer [primary key]
  name varchar [unique]
  unit varchar //Unidad de medida(Kwh,Mwh,Gwk)
}
/*
Tabla: energy_record
Función:
- Tabla transaccional
-almacena los valores históricos de energía
-es la base para reportes y análisis.
*/
Table energy_record{
  id bigint [primary key]
  year integer //año del registro
  month integer//Mes del registro
  value decimal //Valor energético medido
  power_plant_id integer [not null]//FK hacia power_plant
  measurement_type_id integer [not null]//FK hacia measurement_type
indexes {
  (power_plant_id,year,month, measurement_type_id) [unique]
}
}

//dwfine el tipo de Enum
Enum Role{
  ADMIN
  USER
  ANALYST
}
/*
Tabla: users
Función
-Representa los usuarios del sistema
-Gestiona autentificación y roles
-No pertenece al dominio energético
*/

Table users{
  id integer [primary key]
  username varchar [unique]
  role Role [not null]
  email varchar [unique]
  password varchar
  created_at timestamp //Fecha de creación
  updated_at timestamp //Fecha de modificación
}
/*
Tabla: audit_log
Función
-tabla de auditoría
-Registra acciones realizadas por los usuarios
-Garantiza trazabilidad y control
*/
Table audit_log{
  id bigint [primary key]
  action varchar //Acción realizada
  action_date timestamp //fecha de la acción
  user_id integer [not null] //FK hacia users
}
/*
====================================
Relaciones entre tablas
====================================
*/
//Relación geográfica
//Muchas regiones pertenecen a un país
//País -> Región (si se borra país, se borran regiones)
Ref: region.country_id > country.id [delete: cascade]
//Relación empresarial
//Muchas empresas pertenecen a un país
//País -> empresa
Ref: company.country_id > country.id [delete: cascade]
//Relaciones centrales de la planta generadora
//cada planta pertenece a una empresa, región y tipo de energía
//Empresa->planta
Ref: power_plant.company_id > company.id [delete: cascade]
//Región ->planta
Ref: power_plant.region_id > region.id [delete: cascade]
//Tipo de energía -> planta
Ref: power_plant.energy_type_id > energy_type.id [delete: cascade]
//Relación transaccional:
// Muchos registros energéticos pertenecen a una planta
// y a un tipo de medición
Ref: energy_record.power_plant_id > power_plant.id
Ref: energy_record.measurement_type_id > measurement_type.id
//Relación de auditoría:
// Un usuario puede generar muchos registros de auditoría
Ref: audit_log.user_id > users.id