package com.bigdata.agricultureplatform.home.bean;

import java.util.List;

/**
 * @description：$des$
 **/
public class seedtypeBean {

    /**
     * msg : 种子数据请求成功
     * seedtyperesult : [{"seedType":"水稻"},{"seedType":"枸杞"}]
     */

    private String msg;
    private List<SeedtyperesultBean> seedtyperesult;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<SeedtyperesultBean> getSeedtyperesult() {
        return seedtyperesult;
    }

    public void setSeedtyperesult(List<SeedtyperesultBean> seedtyperesult) {
        this.seedtyperesult = seedtyperesult;
    }

    public static class SeedtyperesultBean {
        /**
         * seedType : 水稻
         */

        private String seedType;

        public String getSeedType() {
            return seedType;
        }

        public void setSeedType(String seedType) {
            this.seedType = seedType;
        }
    }
}
