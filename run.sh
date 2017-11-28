#criando imagem do postgreSQL
sudo docker build -t joseph/banco ./postgres
sudo docker run -p 5433:5432 -d --name banco joseph/banco

#criando imagem da aplicação
mvn clean package
sudo docker build -t joseph/aula .
sudo docker run -p 8081:8080 -d --name app --link banco:host-banco joseph/aula
