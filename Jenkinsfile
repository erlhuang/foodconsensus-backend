pipeline {
    agent any

    stages {
        stage('clean') {
            steps {
                sh 'mvn clean'
            }
        }
        stage('package') {
            steps {
                sh 'mvn package'
            }
        }
        // stage('deploy'){
        // 	steps{
        // 		sh 'cp /home/ec2-user/.jenkins/workspace/FoodPipeline/target/AdeptusAdministratum.war /home/ec2-user/DockerStuff/FoodConsensus'
        // 	}
        // }
    }
}