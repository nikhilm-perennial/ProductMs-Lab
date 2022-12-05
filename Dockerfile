FROM openjdk:17
WORKDIR /usr/app
COPY /target/ProductMs.jar /usr/app
EXPOSE 8080
ENTRYPOINT ["java","-jar","ProductMs.jar"]