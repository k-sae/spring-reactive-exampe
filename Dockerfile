FROM maven:3.8.2-jdk-8
WORKDIR /spring-reactive-example
COPY ./.mvn .mvn
COPY ./pom.xml ./
#RUN mvn clean install
RUN ["/usr/local/bin/mvn-entrypoint.sh", "mvn", "verify", "clean", "--fail-never"]
COPY . .
CMD mvn spring-boot:run