def call() {
    script {
        sh "docker build -t notes-app-backend:latest -f backend/Dockerfile.backend ."
        sh "docker build -t notes-app-frontend:latest -f frontend/Dockerfile.frontend ."
    }
}
