package com.bigdata.agricultureplatform.home.bean;

import java.util.List;

/**
 * @description：$des$
 **/
public class AgritimelistinfoBean {

    /**
     * msg : 用户请求农时信息数据请求成功
     * nongshiresult : [{"recommendId":2,"specialistId":1,"seedName":"水稻2号","recommendTime":"2020-04-22","recommendContent":"激发了卡拉就是打开六十六公斤阿拉斯加的；爱丽丝大家噶可是大家gals大驾光临啊拉卡拉少见多怪啊","recommendArea":"永宁,市中心,石嘴山，中卫"},{"recommendId":3,"specialistId":1,"seedName":"水稻三十号","recommendTime":"2021-04-23","recommendContent":"zhege sanshihowoalsjd;glja;ljeoifjaklnfasjl;kjf;asjdg;lkajs;dklgja;lsdjkfl;askhngh;oajsd;gj;klasjgl;kajsdjf","recommendArea":"北方民族大学,石嘴山，中卫"}]
     */

    private String msg;
    private List<NongshiresultBean> nongshiresult;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<NongshiresultBean> getNongshiresult() {
        return nongshiresult;
    }

    public void setNongshiresult(List<NongshiresultBean> nongshiresult) {
        this.nongshiresult = nongshiresult;
    }

    public static class NongshiresultBean {
        /**
         * recommendId : 2
         * specialistId : 1
         * seedName : 水稻2号
         * recommendTime : 2020-04-22
         * recommendContent : 激发了卡拉就是打开六十六公斤阿拉斯加的；爱丽丝大家噶可是大家gals大驾光临啊拉卡拉少见多怪啊
         * recommendArea : 永宁,市中心,石嘴山，中卫
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
