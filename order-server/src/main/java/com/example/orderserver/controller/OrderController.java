package com.example.orderserver.controller;


import com.example.orderserver.domain.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/order")

public class OrderController {

/*    @Autowired
    private OrderInfoMapper orderInfoMapper;*/

    @RequestMapping("/find/{orderNo}")
    public Object selectOrderInfoById(@PathVariable("orderNo") String orderNo, @AuthenticationPrincipal String userName) {
         log.info("userName:{}",userName);
        //OrderInfo orderInfo = orderInfoMapper.selectOrderInfoByIdAndUserName(orderNo,userName);
        /*if(null == orderInfo) {
            return "根据orderNo:"+orderNo+"查询没有该订单";
        }*/

        Map map=new HashMap<String,String>();
        map.put("user_name","bole");
        map.put("order_id",orderNo);
        map.put("amount","100");
        return map;
    }

    @RequestMapping("/save")
    public Object saveOrder(@RequestBody Order orderInfo) {
        log.info("保存订单:{}",orderInfo.toString());
        return orderInfo;
    }



}
