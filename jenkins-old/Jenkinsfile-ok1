pipeline {
    agent any
    stages {
        stage(‘Build’) {
            steps {
                 sh "/opt/apache-maven/bin/mvn clean package"
            }
        }
        stage(‘Test’) {
            steps {
                 sh "/opt/apache-maven/bin/mvn test"
            }
        }
        stage('Deploy') {
            steps {
                sh '/Applications/Docker.app/Contents/Resources/bin/docker-compose/docker-compose up -d --build'
            }
        }
        stage('Push to Docker Hub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub_credentials', passwordVariable: 'DOCKER_HUB_PASSWORD', usernameVariable: 'DOCKER_HUB_USERNAME')]) {
                    sh "/Applications/Docker.app/Contents/Resources/bin/docker login -u $DOCKER_HUB_USERNAME -p $DOCKER_HUB_PASSWORD"
                    sh "/Applications/Docker.app/Contents/Resources/bin/docker tag evalspringse:latest ngorseck/evalspringse:v$BUILD_NUMBER"
                    sh "/Applications/Docker.app/Contents/Resources/bin/docker push ngorseck/evalspringse:v$BUILD_NUMBER"
               }
            }
       }
    }
}
