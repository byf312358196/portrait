package com.byf.map;

import com.alibaba.fastjson.JSONObject;
import com.byf.entity.BrandLike;
import com.byf.kafka.KafkaEvent;
import com.byf.log.ScanProductLog;
import com.byf.util.HbaseUtils;
import com.byf.util.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;

import java.util.HashMap;
import java.util.Map;

public class BrandLikeMap implements FlatMapFunction<KafkaEvent, BrandLike>  {

    @Override
    public void flatMap(KafkaEvent kafkaEvent, Collector<BrandLike> collector) throws Exception {
            String data = kafkaEvent.getWord();
            ScanProductLog scanProductLog = JSONObject.parseObject(data,ScanProductLog.class);
            int userid = scanProductLog.getUserId();
            String brand = scanProductLog.getBrand();
            String tablename = "userflaginfo";
            String rowkey = userid+"";
            String famliyname = "userbehavior";
            String colum = "brandlist";//运营
            String mapdata = HbaseUtils.getdata(tablename,rowkey,famliyname,colum);
            Map<String,Long> map = new HashMap<String,Long>();
            if(StringUtils.isNotBlank(mapdata)){
                map = JSONObject.parseObject(mapdata,Map.class);
            }
            //获取之前的品牌偏好
            String maxprebrand = MapUtils.getMaxMap(map);

            long prebarnd = map.get(brand)==null?0L:map.get(brand);
            map.put(brand,prebarnd+1);
            String finalstring = JSONObject.toJSONString(map);
            HbaseUtils.putdata(tablename,rowkey,famliyname,colum,finalstring);

            String maxbrand = MapUtils.getMaxMap(map);
            if(StringUtils.isNotBlank(maxbrand)&&!maxprebrand.equals(maxbrand)){
                BrandLike brandLike = new BrandLike();
                brandLike.setBrand(maxprebrand);
                brandLike.setCount(-1L);
                brandLike.setGroupbyfield("==brandlik=="+maxprebrand);
                collector.collect(brandLike);
            }

            BrandLike brandLike = new BrandLike();
            brandLike.setBrand(maxbrand);
            brandLike.setCount(1L);
            collector.collect(brandLike);
            brandLike.setGroupbyfield("==brandlik=="+maxbrand);
            colum = "brandlike";
            HbaseUtils.putdata(tablename,rowkey,famliyname,colum,maxbrand);

    }

}
