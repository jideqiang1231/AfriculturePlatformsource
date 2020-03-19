package com.bigdata.agricultureplatform.util;

/**
 * @description：用来配置我们各个页面的联网地址
 **/
public class Constants {
    //用户接口
    public static  String BASE_URL ="http://192.168.1.7:8080/users";
    //    专家接口
    public static  String SPECIALISTBASE_URL="http://192.168.1.7:8080/specialists";
    public static  String LOGIN1_URL =BASE_URL+"/login1";
    public static  String REGISTER_URL =BASE_URL+"/register";
    //专家登录接口
    public static  String SPECIALISTLOGIN_URL=SPECIALISTBASE_URL+"/login";
    //测试的直接路径
    //public static  String LOGIN_URL=BASE_URL+"/login1?userName=admin&userPass=123456";
    //后端图片的直接路径：


}
