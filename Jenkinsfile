pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS_ID = 'dockerhub'
        GITHUB_CREDENTIALS_ID = 'github'
        IMAGE_NAME = 'your-dockerhub-username/spring-boot-app'
        GITHUB_REPO = 'https://github.com/your-username/spring-boot-app.git'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', credentialsId: "${GITHUB_CREDENTIALS_ID}", url: "${GITHUB_REPO}"
            }
        }

        stage('Build JAR') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        
    }
}
