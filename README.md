# filesystemapimongoreaactive
File System Spring Boot API - MongoDB - REACTIVE

# compile
mvn clean install
mvn clean integration-test

#run
mongod --dbpath /Users/marco27/opt/MongoDB/m27data/db --auth
mongod --dbpath /Users/marcoguastalli/opt/MongoDB/m27data/db --auth
mvn clean spring-boot:run

# swagger
http://localhost:8090/swagger-ui.html
Users/marco27/temp -> INVALID 1st Slash

# MongoDB
mongo -u local -p local 127.0.0.1:27017 --authenticationDatabase admin
use local
show collections
db.FileStructure.find()

### play
http://localhost:8090/
%2F

##### GET printPathToFile
http://localhost:8090/printPathToFile/tmp/Users%2Fmarco27%2Ftemp%2Ftmp.txt
http://localhost:8090/printPathToFile/Users%2Fmarco27%2Fopt/Users%2Fmarco27%2Ftemp%2Fopt.txt
http://localhost:8090/printPathToFile/Volumes%2FMAC200%2Fmac200/Users%2Fmarco27%2Ftemp%2Fmac200.txt
http://localhost:8090/printPathToFile/Users%2Fmarcoguastalli%2Fopt%2Fdocker/Users%2Fmarcoguastalli%2Ftemp%2Fdocker.txt
{"pathToPrint":"/Users/marcoguastalli/opt/docker","fileToPrint":"/Users/marcoguastalli/temp/docker.txt"}

##### GET findFileStructureMongoById
http://localhost:8090/findFileStructureMongoById/Users%2Fmarco27%2Ftemp
http://localhost:8090/findFileStructureMongoById/Users%2Fmarcoguastalli%2Ftemp

##### GET findFileStructureMongoByPath
http://localhost:8090/findFileStructureMongoByPath/Users%2Fmarco27%2Ftemp
http://localhost:8090/findFileStructureMongoByPath/Users%2Fmarcoguastalli%2Ftemp

##### POST saveFileStructureMongo
http://localhost:8090/saveFileStructureMongo/Users%2Fmarco27%2Ftemp
http://localhost:8090/saveFileStructureMongo/Users%2Fmarcoguastalli%2FDownloads
curl -X POST --header "Content-Type: application/json" --header "Accept: */*" "http://localhost:8090/saveFileStructureMongo/Users%2Fmarco27%2Ftemp"

##### DELETE deleteFileStructureMongo
http://localhost:8090/deleteFileStructureMongo/Users%2Fmarco27%2Ftemp
http://localhost:8090/deleteFileStructureMongo/Users%2Fmarcoguastalli%2Ftemp
curl -X DELETE --header "Accept: */*" "http://localhost:8090/deleteFileStructureMongo/Users%2Fmarco27%2Ftemp"
curl -X DELETE --header "Accept: */*" "http://localhost:8090/deleteFileStructureMongo/Users%2Fmarcoguastalli%2Ftemp"


{"path":"/Users/marco27/temp","name":"temp","ext":"","timestamp":"2019-11-17 20:51:40","children":[{"path":"/Users/marco27/temp/.DS_Store","name":".DS_Store","ext":"DS_Store","timestamp":"2019-11-17 11:10:42","children":null,"directory":false},{"path":"/Users/marco27/temp/opt.txt","name":"opt.txt","ext":"txt","timestamp":"2019-11-17 20:51:43","children":null,"directory":false}],"directory":true}
