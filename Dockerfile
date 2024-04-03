FROM openjdk:17-jdk-alpine

COPY target/truechoice-0.0.1-SNAPSHOT.jar truechoice-1.0.0.jar

ENTRYPOINT [ "java", "-jar", "truechoice-1.0.0.jar" ]

EXPOSE 8081