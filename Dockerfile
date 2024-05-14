FROM openjdk:17

EXPOSE 8081

ADD ./target/Bmed_Usuarios-1.0.0.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
