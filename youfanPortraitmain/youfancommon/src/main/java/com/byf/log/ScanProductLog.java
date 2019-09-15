package com.byf.log;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScanProductLog implements Serializable {
    private int productId;//商品ID
    private int productTypeId;//商品类别ID
    private String scanTime;//浏览时间
    private String stayTime;//停留时间
    private int userId;//用户ID
    private int userType;// 终端类型，0：PC，1：移动端，2小程序
    private String ip;//用户IP
    private String brand;//商品品牌
}
