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
                archiveArtifacts artifacts: 'build/'
            }
        }
    }
    post {
        success {
            mail to: "ruddanil.work@gmail.com", 
                subject: "${env.JOB_NAME} build ${env.BUILD_NUMBER} - ${currentBuild.currentResult}!",
            body: "${env.JOB_NAME} build ${env.BUILD_NUMBER} - ${currentBuild.currentResult}!\nCheck console output at ${env.BUILD_URL} to view the results"
        }
    }
}
