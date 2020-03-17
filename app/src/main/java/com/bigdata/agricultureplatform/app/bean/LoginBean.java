package com.bigdata.agricultureplatform.app.bean;

/**
 * @description：$des$
 **/
public class LoginBean {

    /**
     * msg : get请求成功
     * code : 200
     * loginresult : {"userId":1,"userName":"admin","userPass":"123456","userPhone":"17863203236"}
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
         * userPhone : 17863203236
         */

        private int userId;
        private String userName;
        private String userPass;
        private String userPhone;

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

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }
    }
}