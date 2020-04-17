package com.bigdata.agricultureplatform.home.bean;

import java.util.List;

/**
 * @description：$des$
 **/
public class RecommendlistinfoBean {

    /**
     * msg : 农技数据请求成功
     * nongjiresult : [{"recommendId":1,"specialistId":1,"seedName":"宁粳43","recommendTime":"2019-11-16","recommendContent":"3月上旬春天地未化通时，进行两遍耙耱保墒整地，用对角耙轻耥，达到地平土碎，以利蓄墒保墒要求；4月初田间潮水落下后，施基肥后及时耙地耥地，镇压后待播。也可采用秋施肥、春免耕的办法保墒播种。播种前对土壤进行普遍镇压，达到提墒和控制播深的目的。","recommendArea":"永宁,灵武,吴忠,青铜峡,中宁,中卫"},{"recommendId":2,"specialistId":1,"seedName":"水稻2号","recommendTime":"2020-04-22","recommendContent":"激发了卡拉就是打开六十六公斤阿拉斯加的；爱丽丝大家噶可是大家gals大驾光临啊拉卡拉少见多怪啊","recommendArea":"永宁,市中心,石嘴山"}]
     */

    private String msg;
    private List<NongjiresultBean> nongjiresult;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NongjiresultBean> getNongjiresult() {
        return nongjiresult;
    }

    public void setNongjiresult(List<NongjiresultBean> nongjiresult) {
        this.nongjiresult = nongjiresult;
    }

    public static class NongjiresultBean {
        /**
         * recommendId : 1
         * specialistId : 1
         * seedName : 宁粳43
         * recommendTime : 2019-11-16
         * recommendContent : 3月上旬春天地未化通时，进行两遍耙耱保墒整地，用对角耙轻耥，达到地平土碎，以利蓄墒保墒要求；4月初田间潮水落下后，施基肥后及时耙地耥地，镇压后待播。也可采用秋施肥、春免耕的办法保墒播种。播种前对土壤进行普遍镇压，达到提墒和控制播深的目的。
         * recommendArea : 永宁,灵武,吴忠,青铜峡,中宁,中卫
         */

        private int recommendId;
        private int specialistId;
        private String seedName;
        private String recommendTime;
        private String recommendContent;
        private String recommendArea;

        public int getRecommendId() {
            return recommendId;
        }

        public void setRecommendId(int recommendId) {
            this.recommendId = recommendId;
        }

        public int getSpecialistId() {
            return specialistId;
        }

        public void setSpecialistId(int specialistId) {
            this.specialistId = specialistId;
        }

        public String getSeedName() {
            return seedName;
        }

        public void setSeedName(String seedName) {
            this.seedName = seedName;
        }

        public String getRecommendTime() {
            return recommendTime;
        }

        public void setRecommendTime(String recommendTime) {
            this.recommendTime = recommendTime;
        }

        public String getRecommendContent() {
            return recommendContent;
        }

        public void setRecommendContent(String recommendContent) {
            this.recommendContent = recommendContent;
        }

        public String getRecommendArea() {
            return recommendArea;
        }

        public void setRecommendArea(String recommendArea) {
            this.recommendArea = recommendArea;
        }
    }
}
