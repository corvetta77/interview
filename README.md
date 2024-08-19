# Info for candidate
This is our most important app for generating bills.

Unfortunately person creating this service left and we need to release it to production today.
The guy was the best and checked everything manually but now jenkins fails on code quality checks. 
Can you do something? We promised to deliver v2 today. Business is waiting for next update in 1 hour.

# Controllers
## BillingController - Provides ability to return reports for customer for current month only, e.g. 
* http://localhost:8080/v1/report?clientId=1
* http://localhost:8080/v1/report?clientId=2
* http://localhost:8080/v1/report?clientId=5

## BillingControllerV2 - Provides ability to return reports for customer and backdate our bills when required, date in the request is in format "yyyy-mm", e.g.: 
* http://localhost:8080/v2/report/3/raw?clientId=3&date=2024-07
* http://localhost:8080/v2/report/4/raw?clientId=4&date=2024-07

In v2 we added possibility to view report as html but date was incorrectly implemented: we can see only reports for current month so far, e.g.:
* http://localhost:8080/v2/report/1/html
* http://localhost:8080/v2/report/2/html 
* http://localhost:8080/v2/report/5/html

# Swagger
http://localhost:8080/swagger-ui.html

### Spring Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.2/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.2/gradle-plugin/packaging-oci-image.html)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#web)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#web.servlet.spring-mvc.template-engines)
* [Rest Repositories](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#howto.data-access.exposing-spring-data-repositories-as-rest)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/3.3.2/reference/htmlsingle/index.html#data.nosql.mongodb)
