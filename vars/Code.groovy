def call(String repoUrl, String branch = 'main') {
    script {
        echo "Cloning the repository..."
        checkout([$class: 'GitSCM',
            branches: [[name: "*/${branch}"]],
            userRemoteConfigs: [[url: repoUrl]]
        ])
        echo "Successfully cloned the repository."
    }
}
