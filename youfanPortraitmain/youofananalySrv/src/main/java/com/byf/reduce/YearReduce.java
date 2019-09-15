package com.byf.reduce;

import com.byf.entity.YearBase;
import org.apache.flink.api.common.functions.ReduceFunction;

public class YearReduce implements ReduceFunction<YearBase> {
    @Override
    public YearBase reduce(YearBase yearBase, YearBase t1) throws Exception {
        String yearType = yearBase.getYearType();
        long count1 = yearBase.getCount();
        long count2 = t1.getCount();
        YearBase finalYearBase = new YearBase();
        finalYearBase.setYearType(yearType);
        finalYearBase.setCount(count1+count2);

        return finalYearBase;
    }
}
