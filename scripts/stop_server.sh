docker ps -aq | xargs --no-run-if-empty docker stop | xargs --no-run-if-empty rm