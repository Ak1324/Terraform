pipeline {
    agent any
    environment {
        IMAGE_NAME = "akshaygoli/demopoc:v1"
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
        stage('Docker Build & Push') {
            steps {
                sh 'docker build -t demopoc .'
                sh 'docker push  ${IMAGE_NAME}'
            }
        }
        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f kubernetes/deployment.yaml'
            }
        }
    }
}
