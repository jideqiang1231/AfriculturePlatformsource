package com.bigdata.agricultureplatform.util;

/**
 * @description：用来配置我们各个页面的联网地址
 **/
public class Constants {
    //用户接口
    public static String BASE_URL = "http://192.168.1.7:8080";
    //
    public static String LOGIN1_URL = BASE_URL + "/users/login1";
    public static String REGISTER_URL = BASE_URL + "/users/register";
    //专家登录
    public static String SPECIALISTLOGIN_URL = BASE_URL + "/specialists/login";

    //种子信息获取
    public static String ZHONGZIINFO_URL = BASE_URL + "/seeds/zhongziinfo";
    //根据专家id获取种子信息得
    public static String SPECIALISTZHONGZIINFO_URL = BASE_URL + "/seeds/specialistzhongzilist";
    //测试的直接路径
    //public static  String LOGIN_URL=BASE_URL+"/login1?userName=admin&userPass=123456";
    //后端图片的直接路径：(水稻)
    public static String ImageBASE_URL = BASE_URL + "/zhongziinfo/";
//public static final String BASE = "http://10.0.2.2:8080";


    //种子图片地址
    public static String SEEDIMAGE = BASE_URL + "/seeds/zhongziimagesave";
    //插入种子信息种子信息地址
    public static String SEEDINFO = BASE_URL + "/seeds/seedinfoinsert";
    //种子根据外键获取（专家基本信息）
    public static String SPECIALISTINFOFORSEEDINFO = BASE_URL + "/specialists/specialistinfo";
    //用户获得农技信息(返回列表信息)
    public static String NONGJIINFO_URL = BASE_URL + "/recommends/nongjiinfo";
    //专家根据其id获取他发布过的农技信息
    public static String SPECIALISTNONGJIINFO_URL = BASE_URL + "/recommends/nongjilistfromspecialistId";
    //专家插入农技信息
   public static String INSERTNONGJIINFO_URL=BASE_URL+"/recommends/nongjiinfoinsert";

    //用户农时
    public static String NONGSHIINFO_URL=BASE_URL+"/recommends/nongshilist";

    public static String ZHENGCEINFO_URL=BASE_URL+"/policies/policiesinfo";
    //注册界面种子类型获取
    public static String GETSEEDTYPE_URL=BASE_URL+"/seeds/getcropstype";
    //注册界面种子名称获取
    public static String GETSEEDNAME_URL=BASE_URL+"/seeds/getcropsname";

    //专家根据其id获取他发布过的政策信息
    public static String SPECIALISTZHENGCEINFO_URL = BASE_URL + "/policies/specialistpoliciesinfo";
    //专家发布政策
    public static String SPECIALISTINSETZHENGCE_URL = BASE_URL + "/policies/policyinfoinsert";

}
