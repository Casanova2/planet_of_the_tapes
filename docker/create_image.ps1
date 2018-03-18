$var = $(get-item ${PWD}).parent.FullName
$pathProject = $var + "\pott"
$pathJar = $pathProject + "\target"

#Create jar pott
docker run -it --rm --name pott -v ${pathProject}:/usr/src/mymaven -w /usr/src/mymaven maven mvn package -DskipTests

#Move jar to actual directory
mv ${pathJar}/planet_of_the_tapes-0.0.1-SNAPSHOT.jar .

#Create image 
docker build -t pott .

