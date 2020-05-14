package com.bigdata.agricultureplatform.home.bean;

import java.util.List;

/**
 * @description：$des$
 **/
public class seednameBean {

    /**
     * msg : 种子名称数据请求成功
     * seednameresult : [{"seedName":"宁粳43"},{"seedName":"枸杞一号"},{"seedName":"水稻2号"},{"seedName":"宁更130"},{"seedName":"水稻20号"},{"seedName":"水稻三十号"},{"seedName":"水稻4号"},{"seedName":"水稻8号"}]
     */

    private String msg;
    private List<SeednameresultBean> seednameresult;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<SeednameresultBean> getSeednameresult() {
        return seednameresult;
    }

    public void setSeednameresult(List<SeednameresultBean> seednameresult) {
        this.seednameresult = seednameresult;
    }

    public static class SeednameresultBean {
        /**
         * seedName : 宁粳43
         */

        private String seedName;

        public String getSeedName() {
            return seedName;
        }

        public void setSeedName(String seedName) {
            this.seedName = seedName;
        }
    }
}
