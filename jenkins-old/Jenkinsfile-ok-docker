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
    }
}
