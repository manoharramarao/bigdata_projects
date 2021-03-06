/**
 *
 */
package com.mylearning.apachestorm.hastagcounter.bolt;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import twitter4j.HashtagEntity;
import twitter4j.Status;

import java.util.Map;

/**
 *
 */
public class HashtagReaderBolt implements IRichBolt{

    private OutputCollector outputCollector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        Status tweet = (Status)tuple.getValueByField("tweet");
        for(HashtagEntity hashtag : tweet.getHashtagEntities()){
            System.out.println("Hashtag: " + hashtag.getText());
            this.outputCollector.emit(new Values(hashtag.getText()));
        }
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("hashtag"));
    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
