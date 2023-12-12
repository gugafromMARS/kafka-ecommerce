# kafka-ecommerce

![Logo](https://github.com/gugafromMARS/kafka-ecommerce/assets/116969206/6e1e353c-7bb1-4baa-b8ae-4aea21fabea6)

Welcome, this is a basic ecommerce for pratice Apache Kafka with microservices. Users make a order, then order service sent from kafka producer to Kafka broker with choosed topic for shipping service, and shipping service resend from kafka producer for the topic that user consumer receive.
Order service, have products, and updates the quantity of all products orded from the user.

## Architecture

![ArchitectureImg](https://github.com/gugafromMARS/event-driven-kafka-spb/assets/116969206/9e26993c-a223-4404-a88b-d88f9ae4b818)


## Technology

Here are some technologys used on this project.

* Java version 17
* Apache Kafka

## Services

Services used.

* Github
  
## Getting started

1- Download Kafka for your pc 

[Kafka download](https://kafka.apache.org/downloads)

2- Run this command on terminal for start Kafka Zookeeper Service
```shell script
bin/zookeeper-server-start.sh config/zookeeper.properties
```
3- At the same time run this command on other terminal tab for start Kafka Broker Service
```shell script
bin/kafka-server-start.sh config/server.properties
```

## App functionalitys

In this project you have different options :

* **Create Order**
  
You choose the products registered on products db, and the quantity. And this send for shipment service and for users service.

* **Create Product**

Create a product, with name, quantity, price.

* **Update Product**

Update price or quantity.

* **Get All Orders From a Product**

Get all orders from a product.

* **Get Order from a user**

* **Etc..**

## Authors

**gugafromMars**

[Github-gugafromMars](https://github.com/gugafromMARS)

Thanks to visiting and happy coding!
