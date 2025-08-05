pipeline {
    agent any
    stages {
        stage('Build') {
            agent {
                 docker { image 'maven:3.9.11-eclipse-temurin-21-alpine' }
            }
            steps {
                 sh "mvn clean package"
            }
        }
        stage('Test') {
            agent {
                 docker { image 'maven:3.9.11-eclipse-temurin-21-alpine' }
            }
            steps {
                 sh "mvn test"
            }
        }
        stage('Push to Docker Hub') {
             steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub_credentials', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USERNAME')]) {
                    sh "docker login -u $DOCKER_HUB_USERNAME -p $DOCKER_HUB_PASSWORD"
                    sh "docker build -t ngorseck/evalspringsecu:v$BUILD_NUMBER ."
                    sh "docker push ngorseck/evalspringsecu:v$BUILD_NUMBER"
               }
            }
       }
    }
}
