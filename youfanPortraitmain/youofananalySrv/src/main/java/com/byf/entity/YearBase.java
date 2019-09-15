package com.byf.entity;

import lombok.Data;

@Data
public class YearBase {
    private String yearType; // 年代类型
    private long count; //数量
    private String groupField;
}
