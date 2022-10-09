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
            subject: "$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS!",
            body: "$PROJECT_NAME - Build # $BUILD_NUMBER - $BUILD_STATUS: Check console output at $BUILD_URL to view the results."
        }
    }
}
