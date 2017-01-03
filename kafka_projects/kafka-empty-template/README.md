# Kafka empty template

This is used to start simple empty kafka project. It could be producer app or consumer app.

## Steps to start

1. Clone the project
2. You can clean README.md and delete pic 1_dir_structure.png under root directory
3. Edit pom.xml and change artifactId and name
4. Change class name under /src/main/java/com/mytutorials/kafka/
5. Edit pom.xml and edit mainClass to match the name and package you are using
6. Do `mvn package`
7. Directory structure is shown below

![1_dir_structure](./1_dir_structure.png)

7. Execute the following command to run the app from project's root directory

`java -jar target/your-app-jar-with-dependencies.jar`