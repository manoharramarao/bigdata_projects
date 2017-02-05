package com.mytutorials.kafkastormintegrator.bolts;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by manohar on 2/5/17.
 */
public class CountBolt implements IRichBolt {

    Map<String, Integer> counters;
    private OutputCollector outputCollector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.counters = new HashMap<String, Integer>();
        this.outputCollector = outputCollector;
    }

    @Override
    public void execute(Tuple input) {
        String str = input.getString(0);

        if(!counters.containsKey(str)){
            counters.put(str, 1);
        }else{
            Integer c = counters.get(str)+1;
            counters.put(str, c);
        }
        outputCollector.ack(input);
    }

    @Override
    public void cleanup() {
        for(Map.Entry<String, Integer> entry: counters.entrySet()){
            System.out.println(entry.getKey()+ " : " + entry.getValue());
        }

    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {

    }

    @Override
    public Map<String, Object> getComponentConfiguration() {
        return null;
    }
}
