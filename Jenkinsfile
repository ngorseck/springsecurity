pipeline {
    agent any
    stages {
        stage(‘Build’) {
            steps {
                  sh "./mvnv clean package"
            }
        }
        stage(‘Test’) {
            steps {
                 sh "./mvnv test"
            }
        }
    }
}
