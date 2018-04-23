# DOCKER DOCUMENTATION
We will show you the basics, from creating the image to running your own containers.

### Table of contents   

[Docker installation](https://github.com/Casanova2/planet_of_the_tapes/blob/master/DOCKER.md#docker-installation)  
[Run the app containerized](https://github.com/Casanova2/planet_of_the_tapes/blob/master/DOCKER.md#run-the-app-containerized-with-docker-compose)  
[Build the image](https://github.com/Casanova2/planet_of_the_tapes/blob/master/DOCKER.md#build-the-image)

## DOCKER INSTALLATION

All the commands in this tutorial requires you to have the latest Docker version software for **any** Operative System, check yours **[here](https://store.docker.com/editions "Download Docker")**.

## RUN THE APP CONTAINERIZED _(with Docker Compose)_

> Compose is a tool for defining and running multi-container Docker applications.

In order to run multi-containers, them being MySQL and Java (JRE server):

* Download the **[docker-compose.yml](https://github.com/Casanova2/planet_of_the_tapes/blob/master/docker/docker-compose.yml)** file.

* Navigate to it's folder and run this command:

   `docker-compose up`
   
   Furthermore, you could run the containers in the background (detached mode) adding:
   
   `-d`

**That's it.**  
Congratulations, now you have two containers up and running our Application and it's MySQL Server.

Now you can access the application on `https://localhost:8080/`
And the SPA with Angular on `https://localhost:8080/angular/`

To stop the containers:

* If you are not running them in detached mode you can press `Ctrl + C`
* Otherwise `docker-compose stop`

## BUILD THE IMAGE

You **_don't_** need to build the image in order to run this application, we host the latest version in **[our repository at DockerHub](https://hub.docker.com/r/pott/pott/)**.

* Run **[this script](https://github.com/Casanova2/planet_of_the_tapes/blob/master/docker/create_image.bat)**, (you don't even need to download the full project, just the script).

* **Or** just run this command:

   `docker build https://github.com/Casanova2/planet_of_the_tapes.git#develop:docker`

   You could name the image whatever you want adding at the end:
   
   `-t imageName`
   
   Or name it to upload your own image later to Dockerhub
   
   `-t user/repository:tag`
   
   And upload it with `docker push user/repository:tag`
   
* **[Full Docker build documentation.](https://docs.docker.com/engine/reference/commandline/build/)**
