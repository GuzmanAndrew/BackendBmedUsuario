pipeline {
  agent { label 'Jenkins-Agent' }
  tools {
    jdk 'Java17'
    maven 'Maven3'
  }
  environment {
	  APP_NAME = "bmed-usuarios-pipeline"
    RELEASE = "1.0.0"
    DOCKER_USER = "andrewramirez"
    DOCKER_PASS = 'Software9525*'
    IMAGE_NAME = "${DOCKER_USER}" + "/" + "${APP_NAME}"
    IMAGE_TAG = "${RELEASE}-${BUILD_NUMBER}"
	  JENKINS_API_TOKEN = credentials("JENKINS_API_TOKEN")
  }
  stages {
    stage("Cleanup Workspace"){
      steps {
        cleanWs()
      }
    }
    stage("Checkout from SCM"){
      steps {
        git branch: 'master', credentialsId: 'github', url: 'https://github.com/GuzmanAndrew/BackendBmedUsuario'
      }
    }
    stage("Build Application"){
      steps {
        sh "mvn clean package"
      }
    }
    stage("Test Application"){
      steps {
        sh "mvn test"
      }
    }
    stage("SonarQube Analysis"){
      steps {
        script {
           withSonarQubeEnv(credentialsId: 'jenkins-sonarqube-tokens') {
              sh "mvn sonar:sonar"
           }
        }
      }
    }
    stage("Build & Push Docker Image") {
      steps {
        script {
          docker.withRegistry('',DOCKER_PASS) {
            docker_image = docker.build "${IMAGE_NAME}"
          }
          docker.withRegistry('',DOCKER_PASS) {
            docker_image.push("${IMAGE_TAG}")
            docker_image.push('latest')
          }
        }
      }
    }
  }
}
