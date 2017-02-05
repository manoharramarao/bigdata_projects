# Example integrating kafka with storm

## Steps to run the application

*Note: below used shell scripts are available in bash_scripts repository under same account*

1. Start zookeeper

   `$ sh bash_scripts/zookeeper_scripts/start_zookeeper.sh`

2. Start kafka broker

   ```bash
   $ sh bash_scripts/kafka_scripts/start_kafka.sh
   ```

3. Create topic

   ```bash
   $ sh bash_scripts/kafka_scripts/create_kafka_topic.sh
   Creating new kafka topic
   ------------------------
   enter zookeeper's host [localhost]: 
   enter zookpper's port [2181]: 
   enter replication factor [1]: 
   enter number of partitions [1]: 
   enter topic name [hello]: test-topic
   ```

4. Use console producer to produce messages into above created topic

   ```bash
   $ kafka/bin/kafka-console-producer.sh --broker-list localhost:9002 --topic test-topic
   test1
   test2
   test3
   test4
   test1 test2 test5 test6 test5 test7 test5
   ```

5. If you want to see these messages in your topic, use default consumer

   ``` bash
   $ bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic test-topic --from-beginning
   test1
   test2
   test3
   test4
   test1 test2 test5 test6 test5 test7 test5
   ```

6. Go to your project directory and do clean and package

   ```bash
   $ cd to/your/project/directory
   $ mvn clean
   $ mvn package
   ```

7. Run the program by executing the following command

   ```bash
   $ /path/to/storm/bin/storm jar target/kafka-storm-integrator-1.0-SNAPSHOT-jar-with-dependencies.jar com.mytutorials.kafkastormintegrator.KafkaStormSample

   ...
   ...
   ...

   test4 : 1
   test5 : 3
   test2 : 2
   test3 : 1
   test6 : 1
   test1 : 2
   ```

   ​