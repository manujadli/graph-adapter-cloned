pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS_ID = 'dockerhub'
        GITHUB_CREDENTIALS_ID = 'github'
        DOCKER_HUB_REPO = 'jasonwick/graph-adapter'
        IMAGE_TAG = 'RELEASE0'
        GITHUB_REPO = 'https://github.com/manujadli/graph-adapter.git'
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

        stage('Build Docker Image') {
            steps {
                script {
                    sh "docker build -t $DOCKER_HUB_REPO:$IMAGE_TAG ."
                }
            }
        }

        stage('Push to Docker Hub') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh "docker push $DOCKER_HUB_REPO:$IMAGE_TAG"
                }
            }
        }
        
    }
}
