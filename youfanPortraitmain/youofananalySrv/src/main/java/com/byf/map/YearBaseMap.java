package com.byf.map;

import com.byf.entity.YearBase;
import com.byf.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.functions.MapFunction;

public class YearBaseMap implements MapFunction<String, YearBase> {

    @Override
    public YearBase map(String s) throws Exception {
        if (StringUtils.isBlank(s)) {
            return null;
        }
        String[] userInfos = s.split(",");
        String userId = userInfos[0];
        String userName = userInfos[1];
        String sex = userInfos[2];
        String telphone = userInfos[3];
        String email = userInfos[4];
        String age = userInfos[5];
        String registerTime = userInfos[6];
        String userType = userInfos[7]; // 终端类型，0：PC，1：移动端，2小程序
        String yearBaseType = DateUtils.getYearBaseByYear(age);
        return null;
    }
}
