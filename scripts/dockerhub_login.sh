username=$(aws ssm get-parameters --region sa-east-1 --name dockerhub_username --with-decryption)
password=$(aws ssm get-parameters --region sa-east-1 --name dockerhub_password --with-decryption)
echo "$username"
echo "$password"
docker login --username username --password password
