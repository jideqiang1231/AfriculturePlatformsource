package com.bigdata.agricultureplatform.home.bean;

import java.util.List;

/**
 * @description：$des$
 **/
public class PolicyfileBean {

    /**
     * msg : 政策文件数据请求成功
     * policyfileresult : [{"policyfileId":1,"policyfileTopic":"禁止焚烧","policyfileLocation":"/政策/焚烧.dox","policyfileRead":1},{"policyfileId":2,"policyfileTopic":"农药政策","policyfileLocation":"/政策/农药.dox","policyfileRead":null},{"policyfileId":3,"policyfileTopic":"化肥政策","policyfileLocation":"/政策/化肥.dox","policyfileRead":null},{"policyfileId":4,"policyfileTopic":"政策","policyfileLocation":"/政策/政策.dox","policyfileRead":null}]
     */

    private String msg;
    private List<PolicyfileresultBean> policyfileresult;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<PolicyfileresultBean> getPolicyfileresult() {
        return policyfileresult;
    }

    public void setPolicyfileresult(List<PolicyfileresultBean> policyfileresult) {
        this.policyfileresult = policyfileresult;
    }

    public static class PolicyfileresultBean {
        /**
         * policyfileId : 1
         * policyfileTopic : 禁止焚烧
         * policyfileLocation : /政策/焚烧.dox
         * policyfileRead : 1
         */

        private int policyfileId;
        private String policyfileTopic;
        private String policyfileLocation;
        private int policyfileRead;

        public int getPolicyfileId() {
            return policyfileId;
        }

        public void setPolicyfileId(int policyfileId) {
            this.policyfileId = policyfileId;
        }

        public String getPolicyfileTopic() {
            return policyfileTopic;
        }

        public void setPolicyfileTopic(String policyfileTopic) {
            this.policyfileTopic = policyfileTopic;
        }

        public String getPolicyfileLocation() {
            return policyfileLocation;
        }

        public void setPolicyfileLocation(String policyfileLocation) {
            this.policyfileLocation = policyfileLocation;
        }

        public int getPolicyfileRead() {
            return policyfileRead;
        }

        public void setPolicyfileRead(int policyfileRead) {
            this.policyfileRead = policyfileRead;
        }
    }
}
