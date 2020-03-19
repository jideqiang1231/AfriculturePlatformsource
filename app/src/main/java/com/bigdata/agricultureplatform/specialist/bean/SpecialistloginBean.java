package com.bigdata.agricultureplatform.specialist.bean;

/**
 * @description：$des$
 **/
public class SpecialistloginBean {

    /**
     * msg : get请求成功
     * specialistloginresult : {"specialistId":1,"specialistName":"zhangsan","specialistPass":"123456","specialistPhone":"1234","specialistAddress":"宁夏回族自治区","specialistInstructions":"水稻宁粳43号保墒旱直播种植栽培方法","specialistType":"水稻 栽培"}
     */

    private String msg;
    private SpecialistloginresultBean specialistloginresult;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public SpecialistloginresultBean getSpecialistloginresult() {
        return specialistloginresult;
    }

    public void setSpecialistloginresult(SpecialistloginresultBean specialistloginresult) {
        this.specialistloginresult = specialistloginresult;
    }

    public static class SpecialistloginresultBean {
        /**
         * specialistId : 1
         * specialistName : zhangsan
         * specialistPass : 123456
         * specialistPhone : 1234
         * specialistAddress : 宁夏回族自治区
         * specialistInstructions : 水稻宁粳43号保墒旱直播种植栽培方法
         * specialistType : 水稻 栽培
         */

        private int specialistId;
        private String specialistName;
        private String specialistPass;
        private String specialistPhone;
        private String specialistAddress;
        private String specialistInstructions;
        private String specialistType;

        public int getSpecialistId() {
            return specialistId;
        }

        public void setSpecialistId(int specialistId) {
            this.specialistId = specialistId;
        }

        public String getSpecialistName() {
            return specialistName;
        }

        public void setSpecialistName(String specialistName) {
            this.specialistName = specialistName;
        }

        public String getSpecialistPass() {
            return specialistPass;
        }

        public void setSpecialistPass(String specialistPass) {
            this.specialistPass = specialistPass;
        }

        public String getSpecialistPhone() {
            return specialistPhone;
        }

        public void setSpecialistPhone(String specialistPhone) {
            this.specialistPhone = specialistPhone;
        }

        public String getSpecialistAddress() {
            return specialistAddress;
        }

        public void setSpecialistAddress(String specialistAddress) {
            this.specialistAddress = specialistAddress;
        }

        public String getSpecialistInstructions() {
            return specialistInstructions;
        }

        public void setSpecialistInstructions(String specialistInstructions) {
            this.specialistInstructions = specialistInstructions;
        }

        public String getSpecialistType() {
            return specialistType;
        }

        public void setSpecialistType(String specialistType) {
            this.specialistType = specialistType;
        }
    }
}
