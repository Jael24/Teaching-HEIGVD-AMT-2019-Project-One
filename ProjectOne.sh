sudo mvn clean install
cp target/AMTProjectOne.war docker/images/payara/war/AMTProjectOne.war
cd docker/topologies/amt-project-one/
sudo docker-compose down
sudo docker-compose up --build -d
