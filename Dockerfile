FROM java:8
ADD ./target/foodConsensus-boot-0.0.1-SNAPSHOT.jar ./target/foodConsensus-boot-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","./target/foodConsensus-boot-0.0.1-SNAPSHOT.jar"]