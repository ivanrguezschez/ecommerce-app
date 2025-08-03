# ecommerce-app
Aplicación E-Commerce cuya parte backend está bajo arquitectura de microservicios basada en el ecosistema tecnológico Spring (Boot, Cloud, Data, Security) y cuya parte frontend está bajo la arquitectura del framework Angular.

## ecommerce-back
Parte backend de la aplicación de gestión E-Commerce.

### config-service
Servicio de configuración centralizada (Spring Cloud Config Server) basada en filesystem el cual gestiona la configuración del resto de servicios de la aplicación.

### discovery-service
Servicio de registro y búsqueda de servicios (Spring Cloud Discovery Server - Eureka) donde registramos los servicios (config, customer) de la aplicación.

### microservices - customer-service
Microservicio de clientes (customers), hace uso de una base de datos MongoDB (docker-compose).

Endpoints:
* Find All
  * GET http://localhost:8091/api/v1/customers
* Find By Id
  * GET http://localhost:8091/api/v1/customers/6809f6b744a3a941e038cfe5
* Save
  * POST http://localhost:8091/api/v1/customers
    * Body
        ```
        {
            "firstName": "Juan",
            "lastName": "Gacía Perez",
            "email": "cliente01@correo.com",
            "phone": "11223344",
            "address": "Pase de la Castellana 100",
            "city": "Madrid"
        }
        ```
* Update
  * PUT http://localhost:8091/api/v1/customers
    * Body
        ```
        {
            "id": "6809f6b744a3a941e038cfe5",
            "firstName": "Juan",
            "lastName": "Gacía Perez",
            "email": "cliente01@correo.com",
            "phone": "11223344",
            "address": "Pase de la Castellana 100",
            "city": "Madrid"
        }
        ```
* Delete By Id
  * DELETE http://localhost:8091/api/v1/customers/6809f6b744a3a941e038cfe5

### microservices - common-exceptions
Módulo común de excepciones para el resto de microservicio (customer, etc).