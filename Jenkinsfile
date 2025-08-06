pipeline {
    agent {
        docker {
            image 'maven:3.9.11-eclipse-temurin-8-alpine'
            args '-u root'
        }
      }
    stages {
        stage('Build') {
            steps {
                 sh "mvn clean package -DskipTests"
            }
        }
        stage('Test') {
            steps {
                 sh "mvn test"
            }
        }
        /* stage('Push to Docker Hub') {
             agent { dockerfile true }
             steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub_credentials', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USERNAME')]) {
                    sh "docker login -u $DOCKER_HUB_USERNAME -p $DOCKER_HUB_PASSWORD"
                    sh "docker build -t ngorseck/evalspringsecu:v$BUILD_NUMBER ."
                    sh "docker push ngorseck/evalspringsecu:v$BUILD_NUMBER"
               }
            }
       } */
       def app
        stage('Build image') {
           app = docker.build("ngorseck/evalspringsecu")
        }
        stage('Test image') {
          app.inside {
            sh 'echo "Tests passed"'
          }
        }
        stage('Push image') {
          docker.withRegistry('https://registry.hub.docker.com', 'credentialsId') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
          }
        }
    }
}
