## database-reader-worker
The connection pool is being managed by [HikariCP](https://github.com/brettwooldridge/HikariCP). It starts with 2 idles connections and can go up to 4 depending the number of requests.
 
##### 1) Let's use the docker command below in order to create the Postgres container. 


`docker run --name dublin-bus -v /$HOME/csv:/var/lib/postgresql/data -e POSTGRES_USERNAME=marcelo_teixeira -e POSTGRES_PASSWORD=abc123 -e POSTGRES_DBNAME=daimler -p 5432:5432 frodenas/postgresql`
    
- Don't forget to replace $HOME with the right path.

- You may use any RDBMS to access the data base, make sure to use the values of POSTGRES_USERNAME, POSTGRES_PASSWORD, POSTGRES_DBNAME and port mentioned above.
 
Now let's locally create a folder named "csv" in your home directory a place the file `siri.20121107.csv` which is the 
one I used (e.g.: ${HOME}/csv/siri.20121107.csv ). After setting up that, the container is able to map files in between host and it.

##### 2) Let's install and run the database-worker.
- Install (both verticles): `gradle clean build`

- Run: `gradle runDB`
- ###### Reset database (optional):  `gradle flywayClean`
 
There should be something like this in the console.

    
     INFO [vert.x-eventloop-thread-1] (MainVerticle.java:45) – Starting DB migrations for jdbc:postgresql://localhost:5432/daimler
     INFO [vert.x-eventloop-thread-1] (MainVerticle.java:48) – Applied 2 migrations to DB
     INFO [vert.x-worker-thread-5] (DatabaseReadsWorkerVerticle.java:37) – Database read worker is now running!
 `The 2 migrations to DB:` 2 scripts that were executed and managed by [FlyWay](https://flywaydb.org/) (src/main/resources/db/migration).
  - The first script essentially regards the creation of table 'bus_stop';
  - The second one copies the CSV data into the bus_stop table. 

## http-verticle
##### 1) Let's install and run the http-verticle.

Make sure you have already installed the application previously, also open a new terminal to execute the following command.

- Run: `gradle runHttp`

There should be something like this in the console.

    
    INFO [vert.x-eventloop-thread-1] (HttpVerticle.java:83) – [HTTP server running on port]: 8080


##### 2) RESTful API Documentation

Copy the content of the file src/main/resources/openapi.yaml and paste in the [Swagger Editor](https://editor.swagger.io/).
Keep in mind that the Try it out button will not work, so for instance you may use curl or postman to try out the end points. 

Regarding questions number 2 and 3, the 'atBusStop' parameter behaves as follows.

    if true it filters the vehicles at a bus stop
    if false it filters the vehicles that are not at a bus stop
    if null it doesn't filter by it

## Cluster
Now that we have both the Http and Database verticles running let's check in one of the terminals used if they have joined the cluster. It should look 
like as follows.

    Members {size:2, ver:2} [
            Member [192.168.1.69]:5701 - 272d3f31-3558-4a48-bca4-08800b1811a7
            Member [192.168.1.69]:5702 - 19d37198-264e-48ee-bd51-675cf3dcd670 this
    ]
Eventually each verticle can be configured to scale up independently from each other.

## Answers
- 1 `curl -X POST "localhost:8080/operators" -H "accept: application/json" -H "Content-Type: application/json" -d "{\"startTime\":1352246400000000,\"endTime\":1352332799000000}"`

- 2 `curl -X POST "localhost:8080/vehicles" -H "accept: application/json" -H "Content-Type: application/json" -d "{\"startTime\":1352246400000000,\"endTime\":1352332799000000,\"operator\":\"CF\"}"`

- 3 `curl -X POST "localhost:8080/vehicles" -H "accept: application/json" -H "Content-Type: application/json" -d "{\"startTime\":1352246400000000,\"endTime\":1352332799000000,\"operator\":\"CF\",\"atBusStop\":true}"`

- 4 `curl -X POST "localhost:8080/vehicles/traces" -H "accept: application/json" -H "Content-Type: application/json" -d "{\"startTime\":1352246400000000,\"endTime\":1352332799000000,\"vehicleId\":43001}"`

## Technologies used
- Java 8
- Lombok 1.18.10
- Log4j 1.2.17
- Vert.x 3.8.2
    - Eclipse Vert.x is event driven and non blocking. This means your app can handle a lot of concurrency using a small number of kernel threads. Vert.x lets your app scale with minimal hardware.
    
- OpenAPI 3 / Swagger
    - OpenAPI Specification (formerly Swagger Specification) is an API description format for REST APIs. An OpenAPI file allows you to describe your entire API.
    
- Docker: [image: frodenas/postgresql, PostgreSQL: 9.6]
    - Docker is a set of platform-as-a-service products that use OS-level virtualization to deliver software in packages called containers.

- HikariCP 3.4.1
    - Fast, simple, reliable. HikariCP is a "zero-overhead" production ready JDBC connection pool.

- Flyway 6.0.6
    - Version control for your database.
    
- jOOQ 3.12.1
    - jOOQ Object Oriented Querying, commonly known as jOOQ, is a light database-mapping software library in Java that implements the active record pattern.
    
- HazelCast 3.12.2
    - Default Cluster Manager used by Vert.x.
