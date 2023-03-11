docker ps -aq | xargs --no-run-if-empty docker stop | xargs --no-run-if-empty docker rm
docker image ls -aq | xargs --no-run-if-empty docker rmi