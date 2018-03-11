FROM openjdk:8

ADD ./build/libs/civbudget-rest-service-*.jar /civbudget.jar

CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","civbudget.jar"]