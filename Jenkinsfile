pipeline {
    agent any
    stages {
        stage(‘Build’) {
            agent {
                docker { image 'maven:3.8.7-eclipse-temurin-11' }
            }
            steps {
                 sh "mvn clean package"
            }
        }
        stage(‘Test’) {
            steps {
                 sh "mvn test"
            }
        }
    }
}
