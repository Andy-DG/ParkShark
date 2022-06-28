pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'jdk-12.0.2'
    }

    stages {
        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Test-Stage') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true test'
            }
        }
    }

    post {
        always {
            publishCoverage adapters: [jacocoAdapter('**/target/site/jacoco/jacoco.xml')]
            junit '**/target/surefire-reports/*.xml'
        }
    }
}