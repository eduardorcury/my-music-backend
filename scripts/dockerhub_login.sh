username=$(aws ssm get-parameters --region sa-east-1 --name dockerhub_username --with-decryption --query "Parameter.Value")
password=$(aws ssm get-parameters --region sa-east-1 --name dockerhub_password --with-decryption --query "Parameter.Value")
echo "$username"
echo "$password"
docker login --username username --password password
