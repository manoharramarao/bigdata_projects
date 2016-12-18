package com.tutorialspoint.apachestorm.topology;

import com.tutorialspoint.apachestorm.bolt.CallLogCounterBolt;
import com.tutorialspoint.apachestorm.bolt.CallLogCreatorBolt;
import com.tutorialspoint.apachestorm.spout.FakeCallLogReaderSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

/**
 *
 */
public class MobileCallLoggerTopology {
    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.setDebug(true);

        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("call-log-reader-spout", new FakeCallLogReaderSpout());
        builder.setBolt("call-log-creator-bolt", new CallLogCreatorBolt())
                .shuffleGrouping("call-log-reader-spout");
        builder.setBolt("call-log-counter-bolt", new CallLogCounterBolt())
                .fieldsGrouping("call-log-creator-bolt", new Fields("call"));
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("LogAnalyserStorm", config, builder.createTopology());
        Thread.sleep(10000);
        localCluster.shutdown();

    }
}
