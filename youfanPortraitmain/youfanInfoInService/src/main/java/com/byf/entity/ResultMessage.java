package com.byf.entity;

import lombok.Data;

@Data
public class ResultMessage {
    private String status;//状态 fail 、 success
    private String message;//消息内容
}
