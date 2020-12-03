package com.example.oauthserver.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Description:
 * @Author: yinmb
 * @Date: 2020/7/18 15:09
 */
public class BCryptPasswordEncoderUtil {
    public static void main(String[] args) {
         System.out.println("order_app password =" + new BCryptPasswordEncoder().encode("order_app"));
    }
}
