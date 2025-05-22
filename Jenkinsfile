pipeline {
    agent any
    
  environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub') // Jenkins credentials ID for Docker Hub (username & password)
        DOCKERHUB_REPO = 'akshaygoli/endtoend' // Change to your Docker Hub repo/image
        IMAGE_TAG = "latest" // Or "${env.BUILD_NUMBER}" for unique tags
    }
    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/Ak1324/Terraform.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
       stages {
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${IMAGE_TAG}")
                }
            }
        }

        stage('Login to Docker Hub') {
            steps {
                script {
                    sh "echo \$DOCKERHUB_CREDENTIALS_PSW | docker login -u \$DOCKERHUB_CREDENTIALS_USR --password-stdin"
                }
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                script {
                    docker.image("${DOCKERHUB_REPO}:${IMAGE_TAG}").push()
                }
            }
        }
    }

        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f kubernetes/deployment.yaml'
            }
        }
    }
}
