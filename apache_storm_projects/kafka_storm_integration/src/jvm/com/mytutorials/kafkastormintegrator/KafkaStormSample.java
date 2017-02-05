package com.mytutorials.kafkastormintegrator;

import com.mytutorials.kafkastormintegrator.bolts.CountBolt;
import com.mytutorials.kafkastormintegrator.bolts.SplitBolt;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.*;
import org.apache.storm.spout.SchemeAsMultiScheme;
import org.apache.storm.topology.TopologyBuilder;

import java.util.UUID;

/**
 * Created by manohar on 2/5/17.
 */
public class KafkaStormSample {

    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.setDebug(false);
        /*config.put(config.TOPOLOGY_MAX_SPOUT_PENDING, 1);*/
        String zkConnString = "localhost:2181";
        String topic = "test-topic";
        org.apache.storm.kafka.BrokerHosts hosts = new ZkHosts(zkConnString);

        SpoutConfig kafkaSpoutConfig = new SpoutConfig(hosts, topic, "/" +
                topic, UUID.randomUUID().toString());
        kafkaSpoutConfig.bufferSizeBytes = 1024 * 1024 * 4;
        kafkaSpoutConfig.fetchSizeBytes = 1024 * 1024 * 4;
        kafkaSpoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("kafka-spout", new KafkaSpout(kafkaSpoutConfig));
        builder.setBolt("word-splitter", new SplitBolt()).shuffleGrouping
                ("kafka-spout");
        builder.setBolt("word-counter", new CountBolt()).shuffleGrouping
                ("word-splitter");

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("KafkaStormTopology", config, builder.createTopology());

        Thread.sleep(10000);
        cluster.shutdown();

    }
}
