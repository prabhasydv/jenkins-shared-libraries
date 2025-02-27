def call(String dockerHubUser, String dockerHubRepo, String backendTag = 'backend', String frontendTag = 'frontend') {
    script {
        echo "Pushing to Docker Hub"
        
        withCredentials([usernamePassword(credentialsId: 'dockerhubcred', 
                                          usernameVariable: 'DOCKER_USER', 
                                          passwordVariable: 'DOCKER_PASS')]) {
            sh "docker login -u $DOCKER_USER -p $DOCKER_PASS"
        }

        // Tag the images
        sh "docker image tag notes-app-backend:latest ${dockerHubUser}/${dockerHubRepo}:${backendTag}"
        sh "docker image tag notes-app-frontend:latest ${dockerHubUser}/${dockerHubRepo}:${frontendTag}"

        // Push images to Docker Hub
        sh "docker push ${dockerHubUser}/${dockerHubRepo}:${backendTag}"
        sh "docker push ${dockerHubUser}/${dockerHubRepo}:${frontendTag}"
        
        echo "Docker images pushed successfully."
    }
}
