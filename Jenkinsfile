pipeline {
    // Run on any available Jenkins agent
    agent any

    environment {
        // Pull the SonarQube token from Jenkins credentials store
        SONAR_TOKEN = credentials('sonarqube-token')
        // Name for the Docker image built later in the pipeline
        DOCKER_IMAGE = "cicd-pipeline-app"
    }

    stages {
        // Stage 1: Pull the latest code from GitHub
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/kingswanzy2020/cicd-pipeline-app.git'
            }
        }

        // Stage 2: Compile the project and package it into a JAR
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        // Stage 3: Run all JUnit tests
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                // Always publish test results, even if tests fail
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
    }
}