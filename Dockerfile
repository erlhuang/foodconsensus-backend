FROM java:8
ADD /home/ec2-user/.jenkins/workspace/FoodPipeline/target/foodConsensus-boot-0.0.1-SNAPSHOT.jar /home/ec2-user/.jenkins/workspace/FoodPipeline/target/foodConsensus-boot-0.0.1-SNAPSHOT.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","foodConsensus-boot-0.0.1-SNAPSHOT.jar"]