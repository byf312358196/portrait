package com.byf.entity;

import lombok.Data;

@Data
public class EmaiInfo {
    private String emailtype;//邮箱类型
    private Long count;//数量
    private String groupfield;//分组字段
}
