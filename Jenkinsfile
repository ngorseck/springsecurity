pipeline {
agent any
stages {
    stage(‘Build’) {
        steps {
             sh "/opt/apache-maven/bin/mvn clean install"
        }
    }
    stage(‘Test’) {
        steps {
             sh "/opt/apache-maven/bin/mvn test"
        }
    }
}
}
