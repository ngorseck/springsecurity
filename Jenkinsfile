pipeline {
    // Chaque stage choisit son propre env
    agent none
    stages {
        stage('Build Maven') {
            agent {
                docker {
                    image 'maven:3.9.11-eclipse-temurin-11-alpine'
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
                    image 'maven:3.9.11-eclipse-temurin-11-alpine'
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
       stage('Deploy on Remote Server') {
            agent any
            steps {
                // SSH agent avec une clé stockée dans Jenkins
                // Dans Jenkins, remote_ssh_key doit être la clé privée, pas la clé publique
                // Clé privée : reste confidentielle, stockée dans Jenkins. Jenkins l’utilise pour s’authentifier auprès du serveur distant.
                   //Ajouter la clé privée dans Jenkins
                   //Va dans Jenkins → Credentials → Global → Add Credentials.
                   //Type : SSH Username with private key.
                   //Username : celui du serveur distant (user).
                   //Private Key : Enter directly → colle le contenu de jenkins_deploy_key.
                   //ID : remote_ssh_key (comme dans ton Jenkinsfile)
                // Clé publique : doit être ajoutée sur le serveur distant dans ~/.ssh/authorized_keys de l’utilisateur cible (user@remote.server.com).
                   sshagent(['remote_ssh_key']) {
                   // Commande distante (tu peux modifier selon ton script)
                   sh """
                       ssh -o StrictHostKeyChecking=no user@remote.server.com '
                       cd /home/user/app &&
                       docker pull ngorseck/evalspringsecu:v$BUILD_NUMBER &&
                       docker stop evalspringsecu || true &&
                       docker rm evalspringsecu || true &&
                       docker run -d --name evalspringsecu -p 8080:8080 ngorseck/evalspringsecu:v$BUILD_NUMBER
                       '
                   """
                }
            }
       }
       stage('Deploy on Remote Server') {
           agent any
           steps {
               script {
                   // Demande de confirmation avant d'exécuter le déploiement
                   def userInput = input(
                       message: 'Voulez-vous déployer sur le serveur distant ?',
                       ok: 'Déployer'
                   )

                   if (userInput != null) {
                       sshagent(['remote_ssh_key']) {
                           sh """
                               ssh -o StrictHostKeyChecking=no user@remote.server.com '
                               cd /home/user/app &&
                               docker pull ngorseck/evalspringsecu:v$BUILD_NUMBER &&
                               docker stop evalspringsecu || true &&
                               docker rm evalspringsecu || true &&
                               docker run -d --name evalspringsecu -p 8080:8080 ngorseck/evalspringsecu:v$BUILD_NUMBER
                               '
                           """
                       }
                   }
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
