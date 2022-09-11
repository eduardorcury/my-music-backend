username=$(aws ssm get-parameters --region sa-east-1 --names dockerhub_username --with-decryption)
password=$(aws ssm get-parameters --region sa-east-1 --names dockerhub_password --with-decryption)
docker login --username username --password password
