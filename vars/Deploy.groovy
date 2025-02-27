def call(String networkName = "notes-app-network", String containerName = "notes-backend") {
    script {
        echo "Starting Docker deployment..."
        
        // Stop and remove container only if running
        sh "docker ps -q -f name=${containerName} && docker compose down || true"

        // Create Docker network if missing
        sh "docker network create ${networkName} || true"

        // Pull missing images
        sh "docker compose pull || true"

        // Deploy using cached layers for faster builds
        sh "docker compose up -d --build --pull missing"

        echo "Docker deployment completed successfully."
    }
}
