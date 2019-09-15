package com.byf.task;

import com.byf.entity.YearBase;
import com.byf.map.YearBaseMap;
import com.byf.reduce.YearReduce;
import com.byf.util.MongoUtils;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.bson.Document;

import java.util.List;

public class YearBaseTask {
    public  static void main(String[] args){
        final ParameterTool params = ParameterTool.fromArgs(args);

        // set up the execution environment
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // make parameters available in the web interface
        env.getConfig().setGlobalJobParameters(params);

        // get input data
        DataSet<String> text = env.readTextFile(params.get("input"));
        DataSet<YearBase> mapResult = text.map(new YearBaseMap());
        DataSet<YearBase> reduceResult = mapResult.groupBy("groupField").reduce(new YearReduce());
        try {
            List<YearBase> resultList = reduceResult.collect();
            for (YearBase yearBase : resultList){
                String yearType = yearBase.getYearType();
                long count = yearBase.getCount();
                Document document = MongoUtils.findoneby("yearbasestatics","youfanPortrait",yearType);
                if (document == null){
                    document = new Document();
                    document.put("info",yearType);
                    document.put("count",count);
                } else {
                    long countpre = document.getLong("count");
                    long total = countpre + count;
                    document.put("count",total);
                }
                MongoUtils.saveorupdatemongo("yearbasestatics", "youfanPortrait",document);
            }
            env.execute("year base analy");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
