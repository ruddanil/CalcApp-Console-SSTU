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
        mail to: ruddanil.work@gmail.com, subject: ‘The Pipeline success :)‘
    }
}
