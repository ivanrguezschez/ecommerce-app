# ecommerce-app
Aplicación E-Commerce cuya parte backend está bajo arquitectura de microservicios basada en el ecosistema tecnológico Spring (Boot, Cloud, Data, Security) y cuya parte frontend está bajo la arquitectura del framework Angular.

## ecommerce-back
Parte backend de la aplicación de gestión E-Commerce.


### config-service
Servicio de configuración centralizada (Spring Cloud Config Server) basada en filesystem el cual gestiona la configuración del resto de servicios de la aplicación.


### discovery-service
Servicio de registro y búsqueda de servicios (Spring Cloud Discovery Server - Eureka) donde registramos los servicios (config, customer) de la aplicación.


### microservices - common-exceptions
Módulo común de excepciones para el resto de microservicio (customer, etc).


### microservices - customer-service
Microservicio de clientes (customers), hace uso de una base de datos MongoDB (docker-compose).

Endpoints:
* Find All
  * GET `http://localhost:8091/api/v1/customers`
* Find By Id
  * GET `http://localhost:8091/api/v1/customers/6809f6b744a3a941e038cfe5`
* Save
  * POST `http://localhost:8091/api/v1/customers`
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
  * PUT `http://localhost:8091/api/v1/customers`
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
  * DELETE `http://localhost:8091/api/v1/customers/6809f6b744a3a941e038cfe5`


### microservices - product-service
Microservicio de productos (products), hace uso de una base de datos PostgreSQL (docker-compose).

Endpoints Category:
* Find All
  * GET `http://localhost:8092/api/v1/categories` 
* Find By Id
  * GET `http://localhost:8092/api/v1/categories/1`
* Save
  * POST `http://localhost:8092/api/v1/categories`
    * Body
        ```
        {
            "name": "Book",
            "description": "Books category"
        }
        ```
* Update
  * PUT `http://localhost:8092/api/v1/categories`
    * Body
        ```
        {
            "id": "1",
            "name": "Book",
            "description": "Books computer science category"
        }
        ```
* Delete By Id
  * DELETE `http://localhost:8092/api/v1/categories/1`

Endpoints Product:
* Find All
  * GET `http://localhost:8092/api/v1/products`
* Find By Id
  * GET `http://localhost:8092/api/v1/products/1`
* Find By Category Id
  * GET `http://localhost:8092/api/v1/products/category/1` 
* Save
  * POST `http://localhost:8092/api/v1/products`
    * Body
        ```
        {
            "name": "Clean Code",
            "description": "A Handbook of Agile Software Craftsmanship",
            "price": "39.99",
            "stock": "5",
            "categoryId": "1"
        }
        ```
* Update
  * PUT `http://localhost:8092/api/v1/products`
    * Body
        ```
        {
            "id": "1",
            "name": "Clean Code",
            "description": "A Handbook of Agile Software Craftsmanship (Prentice Hall)",
            "price": "39.99",
            "stock": "5",
            "categoryId": "1"
        }
        ```
* Delete By Id
  * DELETE `http://localhost:8092/api/v1/products/1`
* Purchase (Comprar)
  * POST `http://localhost:8092/api/v1/products/purchase`
    * Body
        ```
        [
          {
            "productId": "1",
            "quantity": "2",
          },
          {
            "productId": "2",
            "quantity": "4",
          }
        ]
        ```
* Restock (Recargar Stock)
  * POST `http://localhost:8092/api/v1/products/restock`
    * Body
        ```
        [
          {
            "productId": "1",
            "quantity": "10",
          },
          {
            "productId": "2",
            "quantity": "10",
          }
        ]
        ```
