TodoMVC Services - Java EE 6
=====

An example of services to act as a backend for distributed TodoMVC services, written in Java EE 6 using JBoss AS 7.1.1.Final.

Has been tested in JBoss 7.1.1.Final in OS X 10.7.4.

### Service

If the webapp is deployed in a local JBoss instance, it's RESTful service URL is:
http://localhost:8080/todomvc-services-javaee6/todos

It implements the following:
* Get all records & can optionally filter with ?owner=some_value
* Get/push/put/delete a single record

Currently, it is setup to circumvent same origin policy using a CORSFilter based on this [this example][cors], but specifies Access-Control-Allow-Headers as "x-requested-with". If using this for non-public/authenticated use, you can remove the cors package and the filter+filter-mapping from its web.xml.

### Model

Example collection (note: owner is optional):

    [{"id":1,"title":"this is a test","completed":false,"owner":"guest"},{"id":2,"title":"this is a test","completed":false,"owner":"guest"}]

### Database

Assuming using JBoss 7.1.1.Final, it stores values in the default H2 in-memory database with jndi name: java:jboss/datasources/ExampleDS. The JNDI name, etc. can be changed in src/main/java/resources/META-INF/persistence.xml.

### Setup

* Install Java (if necessary).
* Install [Maven 3][mvn]
* Install [JBoss AS 7.1.1][jbossas]

### Start JBoss

In a separate terminal window, do:

    cd jboss-as-7.1.1.Final/bin
    ./standalone.sh

It might help to add an administrative user to be able to check that the war deployed, etc. In a separate terminal window, do:

    cd jboss-as-7.1.1.Final/bin
    ./add-user.sh

### Build

    mvn clean install

### Deploy

    cp target/todomvc-services-javaee6.war jboss-as-7.1.1.Final/standalone/deployments/

### Testing

For those with *nix, can test via curl with:

    ./test.sh

(Currently, that test script requires visual validation, and isn't removing all tasks it creates, due to an issue with the test script that I've not had time to fix.)

### License

Copyright (c) 2012 Gary S. Weaver, released under the [MIT license][lic].

[cors]: http://padcom13.blogspot.com/2011/09/cors-filter-for-java-applications.html
[mvn]: http://maven.apache.org/download.html
[jbossas]: http://www.jboss.org/jbossas/downloads/
[lic]: http://github.com/garysweaver/todomvc-services-javaee6/blob/master/LICENSE
