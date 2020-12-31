FROM java:8
ADD target/foodConsensus-boot-0.0.1-SNAPSHOT.jar target/foodConsensus-boot-0.0.1-SNAPSHOT.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","foodConsensus-boot-0.0.1-SNAPSHOT.jar"]