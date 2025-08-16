pipeline {
    agent any

    tools {
        maven 'Maven-3.9.0'
        jdk 'OpenJDK-17'
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building the project...'
                sh "mvn clean package"
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                sh "mvn test"
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying with Docker Compose...'
                sh 'docker-compose up -d --build'
            }
        }
        stage('Push to Docker Hub') {
            steps {
                echo 'Pushing to Docker Hub...'
                withCredentials([usernamePassword(credentialsId: 'dockerhub_credentials', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USERNAME')]) {
                    sh "docker login -u $DOCKER_HUB_USERNAME -p $DOCKER_HUB_PASSWORD"
                    sh "docker tag evalspringse:latest bounafode/evalspringse:v$BUILD_NUMBER"
                    sh "docker push bounafode/evalspringse:v$BUILD_NUMBER"
               }
            }
       }
    }
}