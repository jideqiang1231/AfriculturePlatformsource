package com.bigdata.agricultureplatform.home.bean;

import java.util.List;

/**
 * @description：$des$
 **/
public class PolicyfileBean {

    /**
     * msg : 政策文件数据请求成功
     * policyfileresult : [{"policyfileId":1,"policyfileLocation":"1223.doc","policyfileRead":1},{"policyfileId":2,"policyfileLocation":"answer.doc","policyfileRead":0},{"policyfileId":3,"policyfileLocation":"policyfile.doc","policyfileRead":0},{"policyfileId":4,"policyfileLocation":"新建文本文档.txt","policyfileRead":0},{"policyfileId":5,"policyfileLocation":"test.txt","policyfileRead":null},{"policyfileId":6,"policyfileLocation":"机器学习_周志华.pdf","policyfileRead":null},{"policyfileId":7,"policyfileLocation":"机器学习_周志华.pdf","policyfileRead":null},{"policyfileId":8,"policyfileLocation":"1235.doc","policyfileRead":null}]
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
         * policyfileLocation : 1223.doc
         * policyfileRead : 1
         */

        private int policyfileId;
        private String policyfileLocation;
        private int policyfileRead;

        public int getPolicyfileId() {
            return policyfileId;
        }

        public void setPolicyfileId(int policyfileId) {
            this.policyfileId = policyfileId;
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
