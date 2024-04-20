# Use an official Maven/Java image as a parent image
FROM maven:3.8-openjdk-17-slim AS build

# Set the working directory to /app
WORKDIR /student-management-system-springboot-main

# Copy the current directory contents into the container at /app
COPY . .

# Build the Java application
RUN mvn clean install

# Use an official MySQL image as the database
FROM mysql:latest

# Set the MySQL root password
ENV MYSQL_ROOT_PASSWORD=@@@Root87

# Set up the database and user
ENV MYSQL_DATABASE=sms
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=@@@Root87

# Copy the database schema script into the container
COPY ./database/schema.sql /docker-entrypoint-initdb.d/

# Expose the port for MySQL
EXPOSE 3306

# Use an official OpenJDK image as the base image
FROM openjdk:17-slim

# Set the working directory to /app
WORKDIR /student-management-system-springboot-main

# Copy the built JAR file from the Maven build stage
COPY --from=build /student-management-system-springboot-main/target/studentrecorddatabase-0.0.1-SNAPSHOT.jar .

# Expose port 8091 for the Spring Boot application
EXPOSE 8091

# Run the Spring Boot application when the container launches
CMD ["java", "-jar", "studentrecorddatabase-0.0.1-SNAPSHOT.jar"]


