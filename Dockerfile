FROM java:8
ADD foodConsensus-boot-0.0.1-SNAPSHOT.jar foodConsensus-boot-0.0.1-SNAPSHOT.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","foodConsensus-boot-0.0.1-SNAPSHOT.jar"]