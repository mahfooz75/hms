# Patient Service
The Patient Microservices architecture is designed to efficiently 
manage and handle patient-related data and operations within a 
healthcare system. This approach leverages the principles of 
microservices, enabling scalability, flexibility, and easy 
integration with other systems or services.

## Getting Started

### Docker Redis
* create `docker-compose.yml` file with following contents
* Run `docker-compose up -d`
* Run `docker exec -it my-redis-container redis-cli` to connect with redis-cli
* Once connected with redis-cli run `KEYS *` to get all redis keys
```yaml
version: '3'
services:
  my-redis-service:
    image: redis
    container_name: my-redis-container
    ports:
      - "6379:6379"
    volumes:
      - D:/Docker-Volume/redis-data:/data
    command: redis-server --appendonly yes
    networks:
      - my-network
networks:
  my-network:
    driver: bridge
```
### Explanation

- **Version**: Specifies the Docker Compose file format version.
- **Services**:
    - **my-redis-service**: Defines the Redis service.
        - **image**: The Docker image to use (`redis`).
        - **container_name**: Name of the container (`my-redis-container`).
        - **ports**: Maps port `6379` on the host to port `6379` in the container.
        - **volumes**: Mounts the local directory `D:/Docker-Volume/redis-data` to `/data` in the container.
        - **command**: Configures Redis to use append-only mode.
        - **networks**: Connects to the `my-network` network.
- **Networks**:
    - **my-network**: Defines a custom network using the `bridge` driver.


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.2/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.2/maven-plugin/build-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#web)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#data.nosql.mongodb)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)




