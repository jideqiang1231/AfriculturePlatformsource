package com.bigdata.agricultureplatform.app.bean;

/**
 * @description：$des$
 **/
public class LoginBean {


    /**
     * msg : get请求成功
     * code : 200
     * loginresult : {"userId":1,"userName":"admin","userPass":"123456","userPhone":"17863203236","userCard":"37132636273838900","userFieldadress":"宁夏北方民族大学","userFieldacres":30,"userCropsname":"宁梗1号","userCropstype":"水稻","userCropsacres":"30"}
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
         * userCard : 37132636273838900
         * userFieldadress : 宁夏北方民族大学
         * userFieldacres : 30.0
         * userCropsname : 宁梗1号
         * userCropstype : 水稻
         * userCropsacres : 30
         */

        private int userId;
        private String userName;
        private String userPass;
        private String userPhone;
        private String userCard;
        private String userFieldadress;
        private double userFieldacres;
        private String userCropsname;
        private String userCropstype;
        private String userCropsacres;

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

        public String getUserCard() {
            return userCard;
        }

        public void setUserCard(String userCard) {
            this.userCard = userCard;
        }

        public String getUserFieldadress() {
            return userFieldadress;
        }

        public void setUserFieldadress(String userFieldadress) {
            this.userFieldadress = userFieldadress;
        }

        public double getUserFieldacres() {
            return userFieldacres;
        }

        public void setUserFieldacres(double userFieldacres) {
            this.userFieldacres = userFieldacres;
        }

        public String getUserCropsname() {
            return userCropsname;
        }

        public void setUserCropsname(String userCropsname) {
            this.userCropsname = userCropsname;
        }

        public String getUserCropstype() {
            return userCropstype;
        }

        public void setUserCropstype(String userCropstype) {
            this.userCropstype = userCropstype;
        }

        public String getUserCropsacres() {
            return userCropsacres;
        }

        public void setUserCropsacres(String userCropsacres) {
            this.userCropsacres = userCropsacres;
        }
    }
}