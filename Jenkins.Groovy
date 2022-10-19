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
        stage('backup') {
            steps {
                bat """
                cd ./CalcApp 
                backup.cmd ${env.BUILD_NUMBER}
                """
            }
        }
       stage('deploy') {
            steps {
                bat """
                cd ./CalcApp 
                deploy.cmd ${env.BUILD_NUMBER}
                """
            }
        }
    }
    post {
        always {
            mail to: "ruddanil.work@gmail.com", 
                subject: "${env.JOB_NAME} build ${env.BUILD_NUMBER} - ${currentBuild.currentResult}!",
            body: "${env.JOB_NAME} build ${env.BUILD_NUMBER} - ${currentBuild.currentResult}!\nCheck console output at ${env.BUILD_URL} to view the results"
        }
    }
}
