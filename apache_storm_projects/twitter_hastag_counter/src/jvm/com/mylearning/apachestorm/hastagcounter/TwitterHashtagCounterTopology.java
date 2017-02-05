/**
 *
 */
package com.mylearning.apachestorm.hastagcounter;

import com.mylearning.apachestorm.hastagcounter.bolt.HashtagCounterBolt;
import com.mylearning.apachestorm.hastagcounter.bolt.HashtagReaderBolt;
import com.mylearning.apachestorm.hastagcounter.spout.TwitterSampleSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

import java.util.Arrays;

/**
 *
 */
public class TwitterHashtagCounterTopology {
    public static void main(String[] args) throws InterruptedException {
        String consumerKey = args[0];
        String consumerSecret = args[1];
        String accessToken = args[2];
        String accessTokenSecret = args[3];

        String[] arguments = args.clone();
        String[] keywords = Arrays.copyOfRange(arguments, 4, arguments.length);

        Config config = new Config();
        config.setDebug(false);

        TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout("twitter-spout", new TwitterSampleSpout(consumerKey, consumerSecret, accessToken,
                accessTokenSecret, keywords));
        topologyBuilder.setBolt("hashtag-reader-bolt", new HashtagReaderBolt()).shuffleGrouping("twitter-spout");
        topologyBuilder.setBolt("hashtag-counter-bolt", new HashtagCounterBolt()).
                fieldsGrouping("hashtag-reader-bolt", new Fields("hashtag"));

        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("TwitterHashtagCounterStorm", config, topologyBuilder.createTopology());
        Thread.sleep(10000);
        cluster.shutdown();
    }
}
