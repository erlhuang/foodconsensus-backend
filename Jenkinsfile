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
        stage('dockerBuild'){
        	steps{
        		sh 'docker build -t foodimage .'
        	}
        }
        stage('dockerRun'){
            steps{
                sh 'docker run -i -p 8081:8081 foodimage'
            }
        }
    }
}