FROM openjdk:21

COPY target/role.jar  /usr/app/

WORKDIR /usr/app/

ENTRYPOINT ["java", "-jar", "role.jar"]
