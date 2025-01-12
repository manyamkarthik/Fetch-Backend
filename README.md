# Receipt Processor Application

This project is a web service designed to fulfill the Receipt Processor API as documented in the `api.yml` file. The service is containerized using Docker for easy setup and execution. Below are detailed instructions for running and testing the application.



## Getting Started

### Prerequisites
To run this application, ensure you have the following installed on your system:  
1. **Docker** (latest version recommended)  
2. A web browser (to access Swagger UI)  
3. **cURL** or **Postman** (optional, for testing API endpoints via command-line or GUI)  

### Environment Details
- The application runs on **port 8080** by default.  
- Ensure port 8080 is free on your system before proceeding.  

## Installation and Setup

### Step 1: Pull the Docker Image
Pull the pre-built Docker image from Docker Hub:  
`docker pull karthikmanyam1/fetch-app:latest`

### Step 2: Run the Docker Container
Run the application container using the following command:  
`docker run -d -p 8080:8080 --name fetch-app karthikmanyam1/fetch-app:latest`  
- **Flags explanation**:  
  - `-d`: Runs the container in detached mode.  
  - `-p 8080:8080`: Maps the container's port 8080 to your machine's port 8080.  
  - `--name fetch-app`: Assigns the name "fetch-app" to the container for easier reference.  

### Step 3: Verify the Application is Running
Check the container logs to confirm the application is running:  
`docker logs fetch-app`  
You should see logs indicating the service has started and is listening on port 8080.  

## Usage

### Access Swagger UI
Swagger UI is available for testing and API documentation. Open your web browser and navigate to:  
`http://localhost:8080/swagger-ui.html`  
From here, you can:  
- View the API documentation.  
- Test API endpoints directly.  

### API Testing with cURL (Optional)
You can also test the endpoints using `cURL` 
Replace the URL, headers, and body as per the API specifications in Swagger.  

## Stopping and Cleaning Up

### Stop the Running Container
To stop the container, run:  
`docker stop fetch-app`

### Remove the Container
If you want to remove the container after stopping it:  
`docker rm fetch-app`

### Remove the Docker Image
If you no longer need the image:  
`docker rmi karthikmanyam1/fetch-app:latest`

## Technical Details
- **Language**: Go (preferred for this project)  
- **API Definition**: OpenAPI Specification (`api.yml` file included)  
- **Port**: 8080  
- **Storage**: In-memory (data is lost when the container stops or is removed)  



## Notes
1. This service is intended for testing purposes and uses in-memory storage. Data persistence is not implemented.  

 
