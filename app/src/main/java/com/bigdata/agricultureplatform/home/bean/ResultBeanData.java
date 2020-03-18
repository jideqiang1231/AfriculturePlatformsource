package com.bigdata.agricultureplatform.home.bean;

/**
 * @description：$des$
 **/
//实验：user表后端返回的内容
public class ResultBeanData {
    /**
     * msg : get请求成功
     * code : 200！
     * loginresult : {"userId":1,"userName":"admin","userPass":"123456","userTell":1542244,"userType":1}
     */
    /**
     * 自带的gsonfomat自动生成javabean对象，别放了勾选fastjson
     * msg : get请求成功
     * result : {"userId":1,"userName":"admin","userPass":"123456","userTell":1542244,"userType":1}
     * code : 200！
     */
    private String msg;
    private String code;
    private LoginresultBean loginresult;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LoginresultBean getLoginresult() {
        return loginresult;
    }

    public void setLoginresult(LoginresultBean loginresult) {
        this.loginresult = loginresult;
    }

    public static class LoginresultBean {
        /**
         * userId : 1
         * userName : admin
         * userPass : 123456
         * userTell : 1542244
         * userType : 1
         */

        private int userId;
        private String userName;
        private String userPass;
        private int userTell;
        private int userType;

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPass() {
            return userPass;
        }

        public void setUserPass(String userPass) {
            this.userPass = userPass;
        }

        public int getUserTell() {
            return userTell;
        }

        public void setUserTell(int userTell) {
            this.userTell = userTell;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }
    }


}
