pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS_ID = 'dockerhub'
        GITHUB_CREDENTIALS_ID = 'github'
        DOCKER_HUB_REPO = 'jasonwick/graph-adapter'
        IMAGE_TAG = 'RELEASE1'
        GITHUB_REPO = 'https://github.com/manujadli/graph-adapter.git'
        ECR_REPO_URL = '885168626435.dkr.ecr.us-west-2.amazonaws.com/graph-adapter'
        AWS_REGION = 'us-west-2'
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

        stage('Authenticate to ECR') {
            steps {
                script {
                    // Use IAM role for authentication (no need for aws-credentials)
                    sh "aws ecr get-login-password --region $AWS_REGION | docker login --username AWS --password-stdin $ECR_REPO_URL"
                }
            }
        }

        stage('Tag & Push Image to ECR') {
            steps {
                script {
                    sh "docker tag $DOCKER_HUB_REPO:$IMAGE_TAG $ECR_REPO_URL:$IMAGE_TAG"
                    sh "docker push $ECR_REPO_URL:$IMAGE_TAG"
                }
            }
        }

        stage('Deploy to EKS') {
            steps {
                script {
                    sh "aws eks update-kubeconfig --region $AWS_REGION --name dummy-cluster"
                    sh "helm upgrade --install graph-adapter $WORKSPACE/graph-adapter"
                }
            }
        }
    }
}
