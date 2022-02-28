FROM openjdk:11
LABEL maintainer="Kenjie Sta. Ana"
ADD target/auth-0.0.1-SNAPSHOT.jar auth-0.0.1.jar
ENTRYPOINT ["java", "-jar", "auth-0.0.1.jar"]
