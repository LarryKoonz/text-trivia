FROM openjdk:11
COPY target/textTrivia*.jar /usr/src/textTrivia.jar
COPY src/main/resources/application.properties /opt/conf/application.properties
CMD ["java", "-jar", "/usr/src/textTrivia.jar", "--spring.config.location=file:/opt/conf/application.properties"]

