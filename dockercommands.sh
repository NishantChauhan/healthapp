#Build
docker build -f tomcat.df -t demo/tomcat:8 .

#Start First Time
docker run -ti -d -p 8080:8080 --name tomcatdev -v "$PWD":/mnt/   demo/tomcat:8

#Start
docker start tomcatdev

#inspect
docker inspect tomcatdev

#logs -tomcatdev is conatiner
docker logs tomcatdev
docker logs springbootapp

#running containers
 docker ps -a

#access
docker exec -ti docker_web_1 /bin/bash

#start compose of containers
docker-compose up

#start compose of containers in background
docker-compose up -d

#stop compose of containers
docker-compose stop

#docker images
docker images

# Build the docker image within project root folder
docker build -t healthapp:latest .

# Login into Docker Hub 
docker login -u="nishantchouhan" -p="dockerhub"

# Tag the docker image
docker tag 58b7028e135d nishantchouhan/healthapp:latest

# Push docker image into Docker Hub 
docker push nishantchouhan/healthapp:latest

#Pull
docker pull nishantchouhan/healthapp:latest

#Run
docker run -tid -p 8080:8080  -v /c/Users:/mnt nishantchouhan/healthapp:latest

docker run -tid -p 8080  -v /c/Users:/mnt nishantchouhan/healthapp:latest


#Misc
cd /c/My/Programming/workspace/HealthApplication-MVC-JSP
docker build -t springboot-app:latest .

docker rm sbapp

docker run -tid -p 8080:8080 --name sbapp springboot-app:latest

docker exec -ti sbapp sh



wget http://localhost:8080/doctors?speciality=Testing

docker network create -d bridge --subnet 172.17.0.0/16 --gateway 172.17.0.2 dockernet
