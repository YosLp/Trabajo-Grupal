# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.5.0-M3/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.5.0-M3/maven-plugin/build-image.html)
* [GraalVM Native Image Support](https://docs.spring.io/spring-boot/3.5.0-M3/reference/packaging/native-image/introducing-graalvm-native-images.html)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/3.5.0-M3/reference/using/devtools.html)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/3.5.0-M3/specification/configuration-metadata/annotation-processor.html)
* [Spring Web](https://docs.spring.io/spring-boot/3.5.0-M3/reference/web/servlet.html)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/3.5.0-M3/reference/web/reactive.html)
* [Spring Web Services](https://docs.spring.io/spring-boot/3.5.0-M3/reference/io/webservices.html)
* [Spring Batch](https://docs.spring.io/spring-boot/3.5.0-M3/how-to/batch.html)
* [Java Mail Sender](https://docs.spring.io/spring-boot/3.5.0-M3/reference/io/email.html)
* [JTE](https://jte.gg/)
* [OAuth2 Client](https://docs.spring.io/spring-boot/3.5.0-M3/reference/web/spring-security.html#web.security.oauth2.client)
* [Spring Security](https://docs.spring.io/spring-boot/3.5.0-M3/reference/web/spring-security.html)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Building a Reactive RESTful Web Service](https://spring.io/guides/gs/reactive-rest-service/)
* [Producing a SOAP web service](https://spring.io/guides/gs/producing-web-service/)
* [Creating a Batch Service](https://spring.io/guides/gs/batch-processing/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)

### Additional Links
These additional references should also help you:

* [Configure AOT settings in Build Plugin](https://docs.spring.io/spring-boot/3.5.0-M3/how-to/aot.html)

## GraphQL code generation with DGS

This project has been configured to use the Netflix DGS Codegen plugin.
This plugin can be used to generate client files for accessing remote GraphQL services.
The default setup assumes that the GraphQL schema file for the remote service is added to the `src/main/resources/graphql-client/` location.

You can learn more about the [plugin configuration options](https://github.com/deweyjose/graphqlcodegen) and
[how to use the generated types](https://netflix.github.io/dgs/generating-code-from-schema/) to adapt the default setup.


## GraalVM Native Support

This project has been configured to let you generate either a lightweight container or a native executable.
It is also possible to run your tests in a native image.

### Lightweight Container with Cloud Native Buildpacks
If you're already familiar with Spring Boot container images support, this is the easiest way to get started.
Docker should be installed and configured on your machine prior to creating the image.

To create the image, run the following goal:

```
$ ./mvnw spring-boot:build-image -Pnative
```

Then, you can run the app like any other container:

```
$ docker run --rm -p 8080:8080 demo:0.0.1-SNAPSHOT
```

### Executable with Native Build Tools
Use this option if you want to explore more options such as running your tests in a native image.
The GraalVM `native-image` compiler should be installed and configured on your machine.

NOTE: GraalVM 22.3+ is required.

To create the executable, run the following goal:

```
$ ./mvnw native:compile -Pnative
```

Then, you can run the app as follows:
```
$ target/demo
```

You can also run your existing tests suite in a native image.
This is an efficient way to validate the compatibility of your application.

To run your existing tests in a native image, run the following goal:

```
$ ./mvnw test -PnativeTest
```


## JTE

This project has been configured to use [JTE precompiled templates](https://jte.gg/pre-compiling/).

However, to ease development, those are not enabled out of the box.
For production deployments, you should remove

```properties
gg.jte.development-mode=true
```

from the `application.properties` file and set

```properties
gg.jte.use-precompiled-templates=true
```

instead.
For more details, please take a look at [the official documentation](https://jte.gg/spring-boot-starter-3/).

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

