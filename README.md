Coverage: 66%
# Inventory Management System

Inventory Management System, with full Create, Read, Update and Delete (CRUD) functionality linking to MySQL database via JDBC.
Users have a choice to manuver between four different tables, being customer, orders, product and order_product. Users can create a customer, create a product, then assign an order using customerId and productId and then further create order_product requiring a customerId, productId and orderId, as well as the quantity of the product(s) that they would like to have.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

The IMS Project has been developed in Java, installing Java will be required in order to run the application. https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html <- Java 17 can be downloaded from here. Choose your operation system (OS) and download Java 17. Once you have successfully installed Java, the next thing that has to be done is set the environment variables. This can be done by nacigating to > "Edit the system environment variable", located at the Control Panel settings, in there, the System Variables have to be altered. In there, the setup of JAVA_HOME variable is needed, with location to the path where Java has been installed.

MySQL Workbench is also needs to be installed, and the same way as Java, the variables have to be added. 

All the dependencies needed in order to run the application have been included in the POM.xml file, additionally, the project comes built with 2 JAR files, one of them having the dependencies as well.

### Installing

First, in order for the project to be ran, it needs to be downloaded onto the local machine. 

![image](https://user-images.githubusercontent.com/97594829/181769988-6f4c31d3-805f-4190-9642-b533b587984c.png)

Click on the green button "Code" and select the HTTPS. Copy the link. Next, choose a place where you would like to clone the GitRepository.

![image](https://user-images.githubusercontent.com/97594829/181770391-d350efff-0430-4746-ad25-50b21f8a4968.png)

Open GitBash or any other command line interface and type ```git clone https://github.com/sisath/InventoryManagementSystem.git```

![image](https://user-images.githubusercontent.com/97594829/181770557-272b9717-2ef7-49aa-b19d-18db3d0ff086.png)

![image](https://user-images.githubusercontent.com/97594829/181770633-865e8444-1b99-4522-b4b5-1a9d3f2edf08.png)

In order to run the application, open any command line interface and type ```java -jar ims-0.0.1-jar-with-dependencies.jar```

Next, it is going to ask for your login details, use the following:

![image](https://user-images.githubusercontent.com/97594829/181771282-0ba5eae7-7261-4ec3-91e0-e99f9821fe14.png)

Now you are fully connected !

![image](https://user-images.githubusercontent.com/97594829/181771347-6c8a4610-74fc-4ff4-bd39-15f697308ab9.png)

Now you are free to create a customer, a product, create an order and also create an order_product.


...................

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

End with an example of getting some data out of the system or using it for a little demo

## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

Explain what these tests test, why and how to run them

```
Give an example
```

### Integration Tests 
Explain what these tests test, why and how to run them

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
