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

## Databse - How It Works

Entity Relationship Diagram

![ERDDiagram](https://user-images.githubusercontent.com/97594829/181777863-d2777d10-1d7a-400a-abdf-cdaa9298400f.png)

This is what the database looks like, they are all interconnected between eachother. Customer allows you to create a
customer by providing a first_name, surname, email and postcode. Once a customer is created, next what you would like to create is a product, by providing a price, product_name and a description. Next, you create an order, by providing a customerId. Finally, you create a order_product where you provide a customer_id, product_id, order_id and quantity, the number of products that you would like to have. Orders, also have a calculating method, where it calculates the total price by multiplying the quantity by the product price. For example, If you order 5 hats, each one of them costing 10.00, you would get 50.00 on your order.

## Project - Demonstration

Let's start by creating a customer.

```Customer{customerId=1, firstName='Stanislav', surname='Angelov', email='stanislav@gmail.com', postCode='EI75GH'}```
![image](https://user-images.githubusercontent.com/97594829/181779932-0d69bc2b-6ee3-4122-b411-d5db4126c2f1.png)

Next, create a product.

```Product{productId=1, price=10.0, productName='Hat', productDescription='A small hat'}```
![image](https://user-images.githubusercontent.com/97594829/181779990-6fa9d14d-11c4-49be-863f-55dc1ecd1cfa.png)

Here, we have created a product with the name "Hat", having a price of 10.00

Time to create the order!

```Order{orderId=1, customer=Customer{customerId=1, firstName='Stanislav', surname='Angelov', email='stanislav@gmail.com', postCode='EI75GH'}, cost=0}```

![image](https://user-images.githubusercontent.com/97594829/181780124-8aad8130-7b75-4f77-ae94-4c8602874417.png)

As you may notice, the cost us currently ```0```, as we have not specified how many "Hats" we would like to order.

```OrderProduct{orderProductId=1, product=Product{productId=1, price=10.0, productName='Hat', productDescription='A small hat'}, order=Order{orderId=1, customer=Customer{customerId=1, firstName='Stanislav', surname='Angelov', email='stanislav@gmail.com', postCode='EI75GH'}, cost=50}, quantity=5}```

![image](https://user-images.githubusercontent.com/97594829/181780379-048cf50e-3e54-4d68-8e6f-c4d8bcf7caa1.png)

We have specified that we want 5 "Hats", and therefore the cost has been calculated to 50!


## Running the tests

The tests for the Inventory Management System applications have been done utilising JUnit and Mockito framework.The tests can be ran within an Integrated Development Environment (IDE). In order to run the tests, navigate to:

![image](https://user-images.githubusercontent.com/97594829/181772309-8e43ed40-a3df-4aac-bc19-43a63cd04421.png)

And right click with the mouse/touchpad and choose:

![image](https://user-images.githubusercontent.com/97594829/181772572-d0000da0-e485-4746-b1ec-8420c08b4599.png)

```Run `All Tests` with Coverage``` 

Ideally, those tests would have a coverage around or above 80%, however, in this case only 66% coverage has been achieved. THis amount has been sufficient for the purpose provided, and with more time and more tests being done a coverage beyond 80% would be easily achievable.

The 66% Coverage only includes the CRUD functioality of the App, that includes Controller & DAO Classes, the rest of the classes have not been fully tested, hence why the lower coverage percentage.

### Unit Tests 

Here's an example of a JUnit test for the testEquals for the OrderProduct class.

```
 @Test
    public void testEquals() {
        EqualsVerifier.simple().forClass(OrderProduct.class).verify();
    }
```
### Integration Tests 

Here's an example of a Mockito test for the testAddProducts() and testRemoveProducts, which were an extension task for the Inventory Mangement System project. Here, we are testing whether the functions are doing what they are expecting to be doing.

Let's have a look at this test, testAddProducts();
    
```
 @Test
    public void testAddProducts() {
        long quantity = 1L;
        OrderProduct created = new OrderProduct(2L, product, order, quantity);

        Mockito.when(utils.getLong()).thenReturn(1L);

        assertEquals(created, controller.addProducts());
        Mockito.verify(utils, Mockito.times(3)).getLong();
    }
```


1. ```long quantity = 1L;
        OrderProduct created = new OrderProduct(2L, product, order, quantity);
        ```
   
In here we are setting the quantity to 1. Next line, we are creating an OrderProduct, with orderProductId of 2, a product, with
productId of 1, price 10.00, productName='Notebook', productDescription='Very nice notebook', then we are creating an order with orderId of 1, a customer with customerId = 1, firstName='Stanislav', surname='Angelov', email='email@gmail.com', postCode='RM92HJ', and a cost=60.

We get this information from a little above where we have initialised the following 

```private final Customer customer = new Customer(1L,"Stanislav", "Angelov", "email@gmail.com", "RM92HJ");
    private final Product product = new Product(1L, 10L, "Notebook", "Very nice notebook");
    private final Order order = new Order(1L, customer, 60L);
    
  ```

2. ```Mockito.when(utils.getLong()).thenReturn(1L);```

In here we are mocking utils: "utils"

3. ```assertEquals(created, controller.addProducts());
        Mockito.verify(utils, Mockito.times(3)).getLong();```
        
 Finally, we are mocking the utils: "utils" again and we get our result back, in this case the test has passed and that is what we would like to see.
 
 
## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Stanislav Angelov** - [sisath](https://github.com/sisath)

## Jira Link

* https://sisoathc.atlassian.net/jira/software/projects/IMS/boards/4/roadmap

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Special thank you to all the amazing tutors who have been guiding us since day 1 at QA Ltd.

