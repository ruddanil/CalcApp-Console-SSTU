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
    }
}
