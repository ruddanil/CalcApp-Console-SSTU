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
                archiveArtifacts allowEmptyArchive: true, artifacts: "CalcApp/bin/Debug/net6.0/**", caseSensitive: false, followSymlinks: false, onlyIfSuccessful: true
                bat """
                cd ./CalcApp 
                backup.cmd
                """
            }
        }
       stage('deploy') {
            steps {
                bat """
                cd ./CalcApp 
                deploy.cmd
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
