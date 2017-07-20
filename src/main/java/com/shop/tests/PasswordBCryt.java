package com.shop.tests;

import com.shop.Utils.BCryptUtil;

public class PasswordBCryt {
    public static void main(String[] args){
        String password = "admin";
        password = BCryptUtil.encode(password);
        System.out.println(password);
    }
}
