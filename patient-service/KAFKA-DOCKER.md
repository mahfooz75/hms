# Kafka Docker

## Docker Compose Setup
* create `docker-compose.yml` file with following contents
* Run `docker-compose up -d`
```yaml
services:
  zookeeper1-service:
    image: confluentinc/cp-zookeeper:latest
    hostname: zookeeper1-host					# hostname is optional, if hostname not available docker used service name as host name
    container_name: zookeeper1-container
    environment:
      ZOOKEEPER_SERVER_ID: 1
      ZOOKEEPER_CLIENT_PORT: 2181
      ZOOKEEPER_TICK_TIME: 2000
    volumes:
      - D:/Docker-Volume/Kafka-Cluster/zookeeper1-data:/var/lib/zookeeper/data
      - D:/Docker-Volume/Kafka-Cluster/zookeeper1-logs:/var/lib/zookeeper/log
    ports:
      - "2181:2181"
    networks:
      - kafka-net

  kafka1-service:
    image: confluentinc/cp-kafka:latest
    hostname: kafka1-host
    container_name: kafka1-container
    ports:
      - "9092:9092"
    environment:
      KAFKA_BROKER_ID: 1
      KAFKA_ZOOKEEPER_CONNECT: "zookeeper1-host:2181"
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka1-host:9092
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 3
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 3
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 2
    volumes:
      - D:/Docker-Volume/Kafka-Cluster/kafka1-data:/var/lib/kafka/data
      - D:/Docker-Volume/Kafka-Cluster/kafka1-logs:/var/log/kafka
    depends_on:
      - zookeeper1-service
    networks:
      - kafka-net

  kafka2-service:
    image: confluentinc/cp-kafka:latest
    hostname: kafka2-host
    container_name: kafka2-container
    ports:
      - "9093:9092"
    environment:
      KAFKA_BROKER_ID: 2
      KAFKA_ZOOKEEPER_CONNECT: "zookeeper1-host:2181"
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka2-host:9092
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 3
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 3
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 2
    volumes:
      - D:/Docker-Volume/Kafka-Cluster/kafka2-data:/var/lib/kafka/data
      - D:/Docker-Volume/Kafka-Cluster/kafka2-logs:/var/log/kafka
    depends_on:
      - zookeeper1-service
    networks:
      - kafka-net

  kafka3-service:
    image: confluentinc/cp-kafka:latest
    hostname: kafka3-host
    container_name: kafka3-container
    ports:
      - "9094:9092"
    environment:
      KAFKA_BROKER_ID: 3
      KAFKA_ZOOKEEPER_CONNECT: "zookeeper1-host:2181"
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka3-host:9092
      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092
      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 3
      KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR: 3
      KAFKA_TRANSACTION_STATE_LOG_MIN_ISR: 2
    volumes:
      - D:/Docker-Volume/Kafka-Cluster/kafka3-data:/var/lib/kafka/data
      - D:/Docker-Volume/Kafka-Cluster/kafka3-logs:/var/log/kafka
    depends_on:
      - zookeeper1-service
    networks:
      - kafka-net
volumes:
  zookeeper1-data:
  zookeeper1-log:
  kafka1-data:
  kafka1-log:
  kafka2-data:
  kafka2-log:
  kafka3-data:
  kafka3-log:
networks:
  kafka-net:
    driver: bridge
```
###### Create Topic
* Connect to kafka broker container, use below command
    * `docker exec -it kafka1-container /bin/bash`
    * Change directory to /usr/bin by using below command
        * `cd /usr/bin`
    * Run below command to create topic
        * `kafka-topics --create --bootstrap-server localhost:9092 --replication-factor 3 --partitions 3 --topic my-topic`.

### Explanation

- **Version**: Specifies the Docker Compose file format version (in this case, version 3.8). Note that in more recent versions of Docker Compose, specifying the version is optional, as the latest version is automatically used.

- **Services**:
    - **zookeeper1-service**: Defines the first Zookeeper service.
        - **image**: The Docker image to use (`confluentinc/cp-zookeeper:latest`), which is a Zookeeper instance from Confluent.
        - **hostname**: Sets the hostname for the container (`zookeeper1-host`), which can be used by other containers in the same network to connect to this service.
        - **container_name**: Name of the container (`zookeeper1-container`).
        - **environment**: Sets environment variables for configuring Zookeeper.
            - `ZOOKEEPER_SERVER_ID`: The unique server ID for this Zookeeper instance.
            - `ZOOKEEPER_CLIENT_PORT`: The port on which Zookeeper listens for client connections.
            - `ZOOKEEPER_TICK_TIME`: The basic time unit in milliseconds used by Zookeeper.
        - **volumes**: Mounts local directories to directories inside the container for persistent data storage and logs.
            - `D:/docker-volume/Kafka-Cluster/zookeeper1-data:/var/lib/zookeeper/data`
            - `D:/docker-volume/Kafka-Cluster/zookeeper1-logs:/var/lib/zookeeper/log`
        - **ports**: Maps port `2181` on the host to port `2181` in the container.
        - **networks**: Connects this service to the `kafka-net` network.

    - **kafka1-service**: Defines the first Kafka broker service.
        - **image**: The Docker image to use (`confluentinc/cp-kafka:latest`), which is a Kafka instance from Confluent.
        - **hostname**: Sets the hostname for the container (`kafka1-host`).
        - **container_name**: Name of the container (`kafka1-container`).
        - **ports**: Maps port `9092` on the host to port `9092` in the container.
        - **environment**: Sets environment variables for configuring Kafka.
            - `KAFKA_BROKER_ID`: The unique broker ID for this Kafka instance.
            - `KAFKA_ZOOKEEPER_CONNECT`: The Zookeeper connection string (host:port) used by Kafka.
            - `KAFKA_ADVERTISED_LISTENERS`: The advertised listeners for clients to connect to.
            - `KAFKA_LISTENERS`: The network interfaces Kafka will listen on.
            - `KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR`: The replication factor for the Kafka offsets topic.
            - `KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR`: The replication factor for the transaction state log.
            - `KAFKA_TRANSACTION_STATE_LOG_MIN_ISR`: The minimum in-sync replicas for the transaction state log.
        - **volumes**: Mounts local directories to directories inside the container for persistent data storage and logs.
            - `D:/docker-volume/Kafka-Cluster/kafka1-data:/var/lib/kafka/data`
            - `D:/docker-volume/Kafka-Cluster/kafka1-logs:/var/log/kafka`
        - **depends_on**: Ensures that the `kafka1-service` only starts after `zookeeper1-service` is up and running.
        - **networks**: Connects this service to the `kafka-net` network.

    - **kafka2-service**: Defines the second Kafka broker service, similar to `kafka1-service`, but with different ports and broker ID.

    - **kafka3-service**: Defines the third Kafka broker service, similar to `kafka1-service`, but with different ports and broker ID.

- **Volumes**: Defines Docker volumes to persist data across container restarts.
    - `zookeeper1-data`, `zookeeper1-log`, `kafka1-data`, `kafka1-log`, `kafka2-data`, `kafka2-log`, `kafka3-data`, `kafka3-log`: These volumes are used by the respective Zookeeper and Kafka services to store their data and logs.

- **Networks**:
    - **kafka-net**: Defines a custom network using the `bridge` driver, which allows all services to communicate with each other.
