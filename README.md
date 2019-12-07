# simpleAPI
> A simple rest API using Spring-boot.
---
## Dependencies
- <a href="https://mail.openjdk.java.net/pipermail/jdk-updates-dev/2019-July/001423.html">openjdk 11.0.4</a>
- <a href="https://maven.apache.org/">Apache Maven</a>
- <a href="http://tomcat.apache.org/tomcat-9.0-doc/">Apache Tomcat 9</a>
## Ιnstructions
- Clone this repository and serve the API
```shell
$ git clone https://github.com/konstantakis/simpleAPI.git
$ cd ./simpleAPI/server
$ mvn clean install (optional)
$ mvn spring-boot:run
```
> You can access the API via the url: http://localhost:8090

> You can change the port by editing the <a href="./server/src/main/resources/application.properties">application.properties</a> file 
- You can use <a href="https://www.getpostman.com/">Postman</a> or the web pages in the directory <a href="./front-end">front-end</a> to test the API.
---
## Examples
> The requests that working are:
```js
POST http://localhost:8090/account?customerId=cus0&initialCredit=50
GET http://localhost:8090/customer/cus0
POST http://localhost:8090/transaction/deposit/acc0?amound=50
```
## Testing
> I created a bash script to test the service.
```shell
$ cd ./simpleAPI/test
$ chmod +x ./service_test.sh
$ ./service_test.sh
```
> Disclaimer: I don't have no experiense or knowlegde of junit test or any other testing technology so I tested the service with this script and with the front-end pages
