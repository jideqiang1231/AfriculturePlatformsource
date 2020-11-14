package com.bigdata.agricultureplatform.util;

/**
 * @description：用来配置我们各个页面的联网地址
 **/
public class Constants {
    //服务器地址 public static final String BASE = "http://10.0.2.2:8080";
    public static String BASE_URL = "http://10.130.161.37:8888";
    //用户登录、注册、专家登录
    public static String LOGIN1_URL = BASE_URL + "/users/login1";
    public static String REGISTER_URL = BASE_URL + "/users/register";
    public static String SPECIALISTLOGIN_URL = BASE_URL + "/specialists/login";
    //种子信息获取、专家通过id获取种子信息
    public static String ZHONGZIINFO_URL = BASE_URL + "/seeds/zhongziinfo";
    public static String SPECIALISTZHONGZIINFO_URL = BASE_URL + "/seeds/specialistzhongzilist";
    //测试的直接路径
    //public static  String LOGIN_URL=BASE_URL+"/login1?userName=admin&userPass=123456";
    //后端图片的直接路径：(水稻)
    public static String ImageBASE_URL = BASE_URL + "/zhongziinfo/";


    //种子图片地址、插入种子信息
    public static String SEEDIMAGE = BASE_URL + "/seeds/zhongziimagesave";
    public static String SEEDINFO = BASE_URL + "/seeds/seedinfoinsert";

    //种子根据外键获取（专家基本信息）
    public static String SPECIALISTINFOFORSEEDINFO = BASE_URL + "/specialists/specialistinfo";
    //用户农技信息、专家根据id获取农技信息、农技信息插入
    public static String NONGJIINFO_URL = BASE_URL + "/recommends/nongjiinfo";
    public static String SPECIALISTNONGJIINFO_URL = BASE_URL + "/recommends/nongjilistfromspecialistId";
   public static String INSERTNONGJIINFO_URL=BASE_URL+"/recommends/nongjiinfoinsert";

    //用户农时、政策信息
    public static String NONGSHIINFO_URL=BASE_URL+"/recommends/nongshilist";
    public static String ZHENGCEINFO_URL=BASE_URL+"/policies/policiesinfo";
    //注册界面种子类型、名称获取
    public static String GETSEEDTYPE_URL=BASE_URL+"/seeds/getcropstype";
    public static String GETSEEDNAME_URL=BASE_URL+"/seeds/getcropsname";

    //政策文本：专家根据其id浏览信息、发布信息
    public static String SPECIALISTZHENGCEINFO_URL = BASE_URL + "/policies/specialistpoliciesinfo";
    public static String SPECIALISTINSETZHENGCE_URL = BASE_URL + "/policies/policyinfoinsert";
    //政策文件：浏览、增加信息、上传、下载
    public static String ZHENGCEWENJIANINFO_URL = BASE_URL + "/policyfiles/policyfileinfo";
    public static String ZHENGCEWENJIANINFOADD_URL = BASE_URL + "/policyfiles/policyfileinfoinsert";
    public static String ZHENGCEWENJIANSHANGCHUAN_URL = BASE_URL + "/policyfiles/uploadPolicyfile";
    public static String ZHENGCEWENJIANXIAZAI_URL = BASE_URL + "/policyfiles/policyfiledownload";

    //专家的三个农资（农机、农药、化肥）查找
    public static String NONGZI_NONGJIINFO_URL = BASE_URL + "/machines/nongjiinfo";
    public static String NONGZI_NONGYAOINFO_URL = BASE_URL + "/pesticides/nongyaoinfo";
    public static String NONGZI_HUAFEIINFO_URL = BASE_URL + "/fertilizers/haufeiinfo";
   //专家的三个农资（农机、农药、化肥）发布
    public static String NONGZI_INSERTNONGJIINFO_URL = BASE_URL + "/machines/agrimachineinfoinsert";
    public static String NONGZI_INSERTNONGYAOINFO_URL = BASE_URL + "/pesticides/pesticideinfoinsert";
    public static String NONGZI_INSERTHUAFEIINFO_URL = BASE_URL + "/fertilizers/fertilizerinfoinsert";
}
