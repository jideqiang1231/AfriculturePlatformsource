package com.bigdata.agricultureplatform.new_all_beans;

/**
 * @description：$des$
 **/
public class recommend {
    /**
     * msg : 刚才插入的农技信息主要部分请求成功，用于下边插入农药跟化肥元素
     * onerecommndresult : {"recommendId":20,"specialistId":1,"recommendTime":"2020-11-22","recommendEndtime":"2020-11-25","seedId":2,"recommendType":4,"recommendReaded":0,"detail":"asdf","notice":"asdfa","stage":"asdf","sowmethod":2}
     */

    private String msg;
    private OnerecommndresultBean onerecommndresult;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public OnerecommndresultBean getOnerecommndresult() {
        return onerecommndresult;
    }

    public void setOnerecommndresult(OnerecommndresultBean onerecommndresult) {
        this.onerecommndresult = onerecommndresult;
    }

    public static class OnerecommndresultBean {
        /**
         * recommendId : 20
         * specialistId : 1
         * recommendTime : 2020-11-22
         * recommendEndtime : 2020-11-25
         * seedId : 2
         * recommendType : 4
         * recommendReaded : 0
         * detail : asdf
         * notice : asdfa
         * stage : asdf
         * sowmethod : 2
         */

        private int recommendId;
        private int specialistId;
        private String recommendTime;
        private String recommendEndtime;
        private int seedId;
        private int recommendType;
        private int recommendReaded;
        private String detail;
        private String notice;
        private String stage;
        private int sowmethod;

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

        public String getRecommendTime() {
            return recommendTime;
        }

        public void setRecommendTime(String recommendTime) {
            this.recommendTime = recommendTime;
        }

        public String getRecommendEndtime() {
            return recommendEndtime;
        }

        public void setRecommendEndtime(String recommendEndtime) {
            this.recommendEndtime = recommendEndtime;
        }

        public int getSeedId() {
            return seedId;
        }

        public void setSeedId(int seedId) {
            this.seedId = seedId;
        }

        public int getRecommendType() {
            return recommendType;
        }

        public void setRecommendType(int recommendType) {
            this.recommendType = recommendType;
        }

        public int getRecommendReaded() {
            return recommendReaded;
        }

        public void setRecommendReaded(int recommendReaded) {
            this.recommendReaded = recommendReaded;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getStage() {
            return stage;
        }

        public void setStage(String stage) {
            this.stage = stage;
        }

        public int getSowmethod() {
            return sowmethod;
        }

        public void setSowmethod(int sowmethod) {
            this.sowmethod = sowmethod;
        }
    }
}
