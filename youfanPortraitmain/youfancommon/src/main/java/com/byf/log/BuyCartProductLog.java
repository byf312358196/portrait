package com.byf.log;

import lombok.Data;

import java.io.Serializable;

@Data
public class BuyCartProductLog implements Serializable {
    private int productId;//商品ID
    private int productTypeId;//商品类别ID
    private String operateTime;//操作时间
    private int operateType;//操作类型，0：加入，1：删除
    private int userId;//用户ID
    private int userType;// 终端类型，0：PC，1：移动端，2小程序
    private String ip;//用户IP
    private String brand;//商品品牌
}
