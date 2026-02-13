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

### microservices - cart-service
Microservicio del carrito de compras (cart), hace uso de una base de datos MongoDB (docker-compose).

Endpoints Cart:
* Find By CustomerId (Obtiene el carrito de compras del cliente)
  * GET `http://localhost:8093/api/v1/{customerId}/cart`
* Delete (Elimina, vacía, el carrito de compras del cliente)
  * DELETE `http://localhost:8093/api/v1/{customerId}/cart`

Endpoints CartItem:
* Create o Save (Agrega un producto al carrito de compras del cliente (no reserva el stock). Crea el carrito si no existe)
  * POST `http://localhost:8093/api/v1/{customerId}/cart/items`
    * Body
      ```
      {
        "productId": "1",
        "quantity": "1",
      }
      ```
* Update (Modifica la cantidad de un item)
  * PUT `http://localhost:8093/api/v1/{customerId}/cart/items`
    * Body
         ```
         {
           "productId": "1",
           "quantity": "2",
         }
         ```
* Delete (Elimina un producto del carrito del cliente)
  * DELETE `http://localhost:8093/api/v1/{customerId}/cart/items/{idProducto}`


### Dockerizando Servicios
Se dockerizan los servicios de config, discovery, customer y product para añadir en un futuro mas servicios y facilitar las pruebas levantando todos los servicios a la vez a través de docker compose.

Notas sobre docker compose:
* Lenvantar los servicios
  * docker-compose up -d --build
    * up: arranca todos los servicios definidos en docker-compose.yml
    * -d: los corre en segundo plano (detached)
    * --build: fuerza la reconstrucción de las imágenes antes de levantar los contenedores
* Lenvantar un servicio concreto
  * docker-compose up -d --build --no-deps <service_name> (Ejemplo: docker-compose up -d --build --no-deps customer-service)
    * --no-deps: no compilar las dependencias (Por ejemplo en el servicio de customer: config-service, discovery-service y mongo)
* Ver qué servicios están corriendo
  * docker-compose ps
* Ver logs en tiempo real
  * docker-compose logs -f discovery-server
    * logs: muestra los logs de un servicio
    * -f: sigue los logs en tiempo real
* Reiniciar un servicio
  * docker-compose restart customer-microservice
* Detener y limpiar todo
  * docker-compose down
  * docker-compose down --rmi local -v
* Detener un servicio concreto
  * docker-compose down <service_name> (Ejemplo: docker-compose down customer-service)

