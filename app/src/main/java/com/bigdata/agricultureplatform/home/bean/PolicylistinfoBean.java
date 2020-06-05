package com.bigdata.agricultureplatform.home.bean;

import java.util.List;

/**
 * @description：$des$
 **/
public class PolicylistinfoBean {

    /**
     * msg : 政策数据请求成功
     * policyresult : [{"policyId":1,"policyTime":"2020-06-13","policyTitle":"宁夏新闻","policyContent":"最近水稻种子不能中了","specialistId":1},{"policyId":2,"policyTime":"2020-06-05","policyTitle":"北方民族大学新闻","policyContent":"某一个种子不错","specialistId":1},{"policyId":3,"policyTime":"2020-06-06","policyTitle":"农科院急报","policyContent":"秸秆焚烧者\u2018杀无赦\u2019","specialistId":2}]
     */

    private String msg;
    private List<PolicyresultBean> policyresult;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<PolicyresultBean> getPolicyresult() {
        return policyresult;
    }

    public void setPolicyresult(List<PolicyresultBean> policyresult) {
        this.policyresult = policyresult;
    }

    public static class PolicyresultBean {
        /**
         * policyId : 1
         * policyTime : 2020-06-13
         * policyTitle : 宁夏新闻
         * policyContent : 最近水稻种子不能中了
         * specialistId : 1
         */

        private int policyId;
        private String policyTime;
        private String policyTitle;
        private String policyContent;
        private int specialistId;

        public int getPolicyId() {
            return policyId;
        }

        public void setPolicyId(int policyId) {
            this.policyId = policyId;
        }

        public String getPolicyTime() {
            return policyTime;
        }

        public void setPolicyTime(String policyTime) {
            this.policyTime = policyTime;
        }

        public String getPolicyTitle() {
            return policyTitle;
        }

        public void setPolicyTitle(String policyTitle) {
            this.policyTitle = policyTitle;
        }

        public String getPolicyContent() {
            return policyContent;
        }

        public void setPolicyContent(String policyContent) {
            this.policyContent = policyContent;
        }

        public int getSpecialistId() {
            return specialistId;
        }

        public void setSpecialistId(int specialistId) {
            this.specialistId = specialistId;
        }
    }
}
