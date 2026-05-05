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

        stage('SonarQube Analysis') {
            steps {
                // Inject SonarQube environment variables using the 'SonarQube' server config
                withSonarQubeEnv('SonarQube') {
                    sh 'mvn sonar:sonar'
                }
            }
        }

        stage('Quality Gate') {
            steps {
                // Wait for SonarQube to return results, fail the build if quality gate fails
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }
        
        stage('Docker Build') {
            steps {
                script {
                    // Build image tagged with the Jenkins build number
                    docker.build("${DOCKER_IMAGE}:${env.BUILD_NUMBER}")
                    // Also tag as latest for convenience
                    docker.build("${DOCKER_IMAGE}:latest")
                    
                sh 'docker images'
                sh 'docker info | grep "Docker Root Dir"'    
                }
            }
        }                
    }

    post {
        success {
            // Send a green notification when the build passes
            slackSend(
                channel: '#jenkins-builds',
                color: 'good',
                message: "SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)"
            )
        }
        failure {
            // Send a red notification when the build fails
            slackSend(
                channel: '#jenkins-builds',
                color: 'danger',
                message: "FAILED: ${env.JOB_NAME} #${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)"
            )
        }
    }    
}