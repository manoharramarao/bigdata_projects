package com.mytutorials.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerDeserializer;

import java.util.Properties;

public class ProducerApp
{

    public static void main(String[] args) {

        if(args.length == 0){
            System.out.println("Enter topic name");
        }
        String topicName = args[0].toString();
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization" +
                ".StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization" +
                ".StringSerializer");
        Producer<String, String> producer = new KafkaProducer<String, String>
                (props);
        for(int i=0; i<10; i++){
            producer.send(new ProducerRecord<String, String>(topicName,
                    Integer.toString(i), Integer.toString(i)));
        }
        System.out.println("Message sent successfully");
        producer.close();


    }
}
