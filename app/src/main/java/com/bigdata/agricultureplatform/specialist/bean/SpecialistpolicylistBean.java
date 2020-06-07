package com.bigdata.agricultureplatform.specialist.bean;

import java.util.List;

/**
 * @description：$des$
 **/
public class SpecialistpolicylistBean {

    /**
     * msg : 政策数据请求成功
     * specialistseedresult : [{"policyId":1,"policyTime":"2020-06-13","policyTitle":"宁夏新闻","policyContent":"最近水稻种子不能中了","specialistId":1},{"policyId":2,"policyTime":"2020-06-05","policyTitle":"北方民族大学新闻","policyContent":"某一个种子不错","specialistId":1},{"policyId":4,"policyTime":"2020-01-05","policyTitle":"秸秆焚烧","policyContent":"最近宁夏农科院发布关于秸秆焚烧的禁令","specialistId":1}]
     */

    private String msg;
    private List<SpecialistseedresultBean> specialistseedresult;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<SpecialistseedresultBean> getSpecialistseedresult() {
        return specialistseedresult;
    }

    public void setSpecialistseedresult(List<SpecialistseedresultBean> specialistseedresult) {
        this.specialistseedresult = specialistseedresult;
    }

    public static class SpecialistseedresultBean {
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
