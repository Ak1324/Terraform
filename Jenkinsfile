pipeline {
    agent any
    environment {
        DOCKERHUB_CREDENTIALS = credentials('dockerhub') // Jenkins credentials ID for Docker Hub
        DOCKERHUB_REPO = 'akshaygoli/endtoend' // Change to your Docker Hub repo/image
        IMAGE_TAG = "latest" // Or "${env.BUILD_NUMBER}" for unique tags
        KUBECONFIG = '/home/ubuntu/.kube/config'
    }
    stages {
        stage('Checkout') {
            steps {
        git branch: 'main', url: 'https://github.com/Ak1324/Terraform.git'
    }
        }
       stage('Build') {
    steps {
        sh 'mvn clean package'
        sh 'ls -l target/*.jar'
    }
    }   
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("${DOCKERHUB_REPO}:${IMAGE_TAG}")
                }
            }
        }
        stage('Login to Docker Hub') {
            steps {
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Push Image to Docker Hub') {
            steps {
                script {
                    docker.image("${DOCKERHUB_REPO}:${IMAGE_TAG}").push()
                }
            }
        }
       stage('Deploy to Minikube') {
      steps {
        sh '''
            export KUBECONFIG=/home/ubuntu/.kube/config
            kubectl get nodes
            kubectl apply -f deployment.yaml
        '''
       }
    }
        }
    }
    post {
        always {
            cleanWs()
        }
    }
}
