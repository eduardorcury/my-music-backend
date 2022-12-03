username=$(aws ssm get-parameters --region sa-east-1 --name dockerhub_username --with-decryption --query "Parameters[0].Value" --output text)
aws ssm get-parameters --region sa-east-1 --name dockerhub_password --with-decryption --query "Parameters[0].Value" --output text | docker login --username $username --password-stdin
