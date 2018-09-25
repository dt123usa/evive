# evive
Evive code challenge with movie DB

This is a Maven Spring Boot project. After downloading the project you can do the following to get it runnin.

Goto the repository where the project was downloaded and execute the following commands:
mvn clean install
mvn spring-boot:run

The application will be up and running on port 8080. You can use the following endpoint to get a count of cast members that were in 
a movie and a tv show that was released durring the timeframe requested.

http://localhost:8080/compare/cast

There are 2 query parameters available to refine your search to specific dates movies and tv shows were released.  

start_date  format: YYYY-MM-DD  default value: 2017-12-01 
end_date    format: YYYY-MM-DD  default VALUE: 2017-12-02

Here is an example endpoint to use 
http://localhost:8080/compare/cast?start_date=2017-12-01&end_date=2017-12-31

The response is an integer value representing the number of cast members that were in both a movie and a tv show released between the start and end dates given.
