docker stop $(docker ps -q)
docker container prune
docker rmi my-music:latest