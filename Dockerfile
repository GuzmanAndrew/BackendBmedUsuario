FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu as base

EXPOSE 8081

ADD ./target/Bmed_Usuarios-1.0.0.jar app.jar

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
