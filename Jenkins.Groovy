pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                bat """
                cd ./CalcApp 
                build.cmd
                """
            }
        }
        stage('tests') {
            steps {
                bat """
                cd ./CalcApp 
                RunTests.cmd
                """
            }
        }
    }
    post {
        success {
            mail to: "ruddanil.work@gmail.com", 
                subject: "${env.PROJECT_NAME} build ${env.BUILD_NUMBER} - ${env.BUILD_STATUS}!",
            body: "${env.PROJECT_NAME} build ${env.BUILD_NUMBER} - ${env.BUILD_STATUS}!\nJob - ${env.JOB_NAME}\nMore Check console output at ${env.BUILD_URL} to view the results"
        }
    }
}
