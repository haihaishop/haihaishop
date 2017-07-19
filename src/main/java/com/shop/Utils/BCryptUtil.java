package com.shop.Utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by 18240 on 2017/7/19.
 */
public class BCryptUtil {
    public static String encode(String password){
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        String hashPass = encode.encode(password);
        return hashPass;
    }
}
