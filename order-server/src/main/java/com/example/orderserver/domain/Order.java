package com.example.orderserver.domain;

import lombok.Data;

/**
 * @Description:
 * @Author: yinmb
 * @Date: 2020/6716 14:08
 */
@Data
public class Order {

    private String order_no;
    private String product_id;
    private String user_name;
    private String amount;

}
