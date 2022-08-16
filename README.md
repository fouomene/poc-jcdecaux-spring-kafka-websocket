# poc-jcdecaux-spring-kafka-websocket
We will use this JCDecaux API https://developer.jcdecaux.com to observe rentals at each bike station in real time...

Architecture 
![alt text](https://fouomene.com/architecture-event-driven.jpg) 

# Step 1 : Get API key 

To start, you will need to retrieve an API key by creating an account at https://developer.jcdecaux.com/#/signup. Once you have created your account, you will have an API key displayed in your user account. If your API key is "XXX", you can verify that it is working correctly by retrieving the list of all stations using the following command:
$ curl https://api.jcdecaux.com/vls/v1/stations?apiKey=XXX

In spring configuration file **application.properties** of module *producer-station* project, put your apiKey 

    jcdecaux.api.key= your apiKey


# Step 2 : Get Kafka

Download https://kafka.apache.org the latest Kafka release and extract it:

    $tar -xzf kafka_2.13-3.2.0.tgz

    $cd kafka_2.13-3.2.0


# Step 3 : Start the Kafka environment

NOTE: Your local environment must have Java 8+ installed.

Run the following commands in order to start all services in the correct order:

- **Start the ZooKeeper service**

-Note: Soon, ZooKeeper will no longer be required by Apache Kafka.

    $bin/zookeeper-server-start.sh config/zookeeper.properties

or window

    $.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties

Open another terminal session and run:

- **Start the Kafka broker service**


    $bin/kafka-server-start.sh config/server.properties

or window

    $.\bin\windows\kafka-server-start.bat .\config\server.properties

Once all services have successfully launched, you will have a basic Kafka environment running and ready to use. 

# Step 4 : Compile, Test & Package project 
    $mvn clean intall
To built .jar file into your local Maven repository

# Step 5 : Run Spring Boot app(s) Producer and Consumer JCDecaux
 - **Producer Station JCDecaux**


    $cd producer-station
    $mvn spring-boot:run

- **Consumer Station JCDecaux**


    $cd consurmer-station
    $mvn spring-boot:run

Websocket Dashboard JCDecaux Producer
![alt text](https://fouomene.com/producerdashboard.jpg) 

Start JCDecaux Producer
![alt text](https://fouomene.com/startproducer.jpg) 

Websocket Dashboard JCDecaux Consumer
![alt text](https://fouomene.com/consumerdashboard.jpg) 

Kafka Manager
![alt text](https://fouomene.com/managerkafka.jpg)

