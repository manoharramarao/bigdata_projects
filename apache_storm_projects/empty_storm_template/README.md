Empty storm template
====================

This template is used to create empty storm project. 
Copy all the contents of the root directory into your project directory and change the following items

1. Edit pom.xml in the / directory
   1. Change the value of artifactId to project name
   2. Change the value of name element to project name
   3. Change the value of groupId to root package name
2. Execute the following commands (assuming maven is already installed)
   1. `$mvn package`
3. Import this maven project into IntelliJ
4. create root package and start implementing spouts/bolts and topologies
5. Follow the following steps to run the project
   1. cd to / of project
   2. `$mvn package`
   3. From root dir execute the following command
      1. `$/path/to/storm/bin/storm jar target/projectname-jar-with-dependencies.jar package.of.mainclass.mainclass`