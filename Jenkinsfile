pipeline {
    // Chaque stage choisit son propre env
    agent none
    stages {
        stage('Build Maven') {
            agent {
                docker {
                    image 'maven:3.9.11-eclipse-temurin-8-alpine'
                    args '-u root -v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                 sh "mvn clean package -DskipTests"
            }
        }
        stage('Unit Test') {
            agent {
                docker {
                    image 'maven:3.9.11-eclipse-temurin-8-alpine'
                    args '-u root -v $HOME/.m2:/root/.m2'
                }
            }
            steps {
                sh "mvn test"
            }
        }
        stage('Push to Docker Hub') {
            agent {
                docker {
                    image 'docker:25.0.3'
                    args '-u root -v /var/run/docker.sock:/var/run/docker.sock'
                }
            }
             steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub_credentials', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USERNAME')]) {
                    sh "docker login -u $DOCKER_HUB_USERNAME -p $DOCKER_HUB_PASSWORD"
                    sh "docker build -t ngorseck/evalspringsecu:v$BUILD_NUMBER ."
                    sh "docker push ngorseck/evalspringsecu:v$BUILD_NUMBER"
               }
            }
       }
    }
     post {
        success {
           echo "Pipeline build successfuly"
        }
        failure {
           echo "Pipeline failed"
        }
     }
}
