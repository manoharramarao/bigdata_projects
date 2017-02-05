# How to execute

*All examples are picked from https://www.tutorialspoint.com/apache_kafka/index.htm*

*Note: Following steps are dependent on bash scripts under https://github.com/manoharramarao/bashScripts.git and simple kafka producer under the same repository*

*All scripts are executed from with in bash_scripts directory*

1. See the java processes running by using `jps` command
2. Start zoo keeper. 

`$ sh zookeeper_scripts/start_zookeeper.sh`


3. Start kafka broker

`$ sh kafka_scripts/start_broker.sh  `

4. Run consumer group app in 3 different terminals. First 2 terminals with group1 and the other with consumer group group2

   1. Terminal1 and Terminal2

   `$ java -jar target/kafka-group-consumer-1.0-SNAPSHOT-jar-with-dependencies.jar hello group1`

   2. Terminal3

   `$ java -jar target/kafka-group-consumer-1.0-SNAPSHOT-jar-with-dependencies.jar hello group2`

5. Create topic by name hello and have 4 partitions. 

```\$sh kafka_scripts/create_kafka_topic.sh 
sh kafka_scripts/create_kafka_topic.sh
Creating new kafka topic
------------------------
enter zookeeper's host [localhost]: 
enter zookpper's port [2181]: 
enter replication factor [1]: 
enter number of partitions [1]: 4
enter topic name [hello]: 
```

6. Run simple producer

`java -jar target/kafka-simple-producer-1.0-SNAPSHOT-jar-with-dependencies.jar`

7. You should see all messages in Terminal3. But messages will be shared between Terminal1 and Terminal2. Here are the values during testing

   1. Terminal 1

      ```
      offset = 0, key = 4, value = 4
      offset = 0, key = 0, value = 0
      offset = 1, key = 2, value = 2
      ```

   2. Terminal 2

      ```
      offset = 0, key = 1, value = 1
      offset = 1, key = 3, value = 3
      offset = 2, key = 7, value = 7
      offset = 3, key = 8, value = 8
      offset = 4, key = 9, value = 9
      offset = 0, key = 5, value = 5
      offset = 1, key = 6, value = 6
      ```

   3. Terminal 3

      ```
      offset = 0, key = 4, value = 4
      offset = 0, key = 5, value = 5
      offset = 1, key = 6, value = 6
      offset = 0, key = 1, value = 1
      offset = 1, key = 3, value = 3
      offset = 2, key = 7, value = 7
      offset = 3, key = 8, value = 8
      offset = 4, key = 9, value = 9
      offset = 0, key = 0, value = 0
      offset = 1, key = 2, value = 2
      ```

   ​
