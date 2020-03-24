package com.bigdata.agricultureplatform.specialist.bean;

import java.util.List;

/**
 * @description：$des$
 **/
public class SpecialistseedlistBean {
//主要的还是一个msg和一个list<SpecialistseedresultBean>包含多个SpecialistseedresultBean
    /**
     * msg : 种子数据请求成功
     * specialistseedresult : [{"seedId":1,"seedName":"宁粳43","seedIntroduce":"宁粳43是以宁粳12\u201d为母本，意大利4号为父本，杂交繁育出F1代水稻，再将它作为母本与父本夏温37进行杂交，这样进行三交组合，培育出的国际顶尖级优质水稻新品种。","seedPlantarea":"贺兰、永宁、灵武、吴忠、青铜峡、中宁、中卫","seedMethod":"插秧","seedPrice":2,"seedManufacturer":"科泰种业","seedNote":"种子非常好","seedStore":"王太堡","seedPhone":"188888888","seedProductiondate":"2019-11-16","seedShelflife":"2020-09-01","seedPlantnumber":"机插秧750-800g/m2。","seedType":"水稻","seedImage":"shuidao/1.jpg"},{"seedId":3,"seedName":"水稻101大牛号","seedIntroduce":"种子耐热性，适合各种季节种植","seedPlantarea":"北方民族大学20号楼","seedMethod":"土埋上就可以","seedPrice":0.1,"seedManufacturer":"大数据有限公司","seedNote":"这款种子绝对虚构","seedStore":"我的小卖铺","seedPhone":"111111100000","seedProductiondate":"2020-03-24","seedShelflife":"2020-05-29","seedPlantnumber":"放一陀","seedType":"水稻","seedImage":"shuidao/3.jpg"}]
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
         * seedId : 1
         * seedName : 宁粳43
         * seedIntroduce : 宁粳43是以宁粳12”为母本，意大利4号为父本，杂交繁育出F1代水稻，再将它作为母本与父本夏温37进行杂交，这样进行三交组合，培育出的国际顶尖级优质水稻新品种。
         * seedPlantarea : 贺兰、永宁、灵武、吴忠、青铜峡、中宁、中卫
         * seedMethod : 插秧
         * seedPrice : 2.0
         * seedManufacturer : 科泰种业
         * seedNote : 种子非常好
         * seedStore : 王太堡
         * seedPhone : 188888888
         * seedProductiondate : 2019-11-16
         * seedShelflife : 2020-09-01
         * seedPlantnumber : 机插秧750-800g/m2。
         * seedType : 水稻
         * seedImage : shuidao/1.jpg
         */

        private int seedId;
        private String seedName;
        private String seedIntroduce;
        private String seedPlantarea;
        private String seedMethod;
        private double seedPrice;
        private String seedManufacturer;
        private String seedNote;
        private String seedStore;
        private String seedPhone;
        private String seedProductiondate;
        private String seedShelflife;
        private String seedPlantnumber;
        private String seedType;
        private String seedImage;

        public int getSeedId() {
            return seedId;
        }

        public void setSeedId(int seedId) {
            this.seedId = seedId;
        }

        public String getSeedName() {
            return seedName;
        }

        public void setSeedName(String seedName) {
            this.seedName = seedName;
        }

        public String getSeedIntroduce() {
            return seedIntroduce;
        }

        public void setSeedIntroduce(String seedIntroduce) {
            this.seedIntroduce = seedIntroduce;
        }

        public String getSeedPlantarea() {
            return seedPlantarea;
        }

        public void setSeedPlantarea(String seedPlantarea) {
            this.seedPlantarea = seedPlantarea;
        }

        public String getSeedMethod() {
            return seedMethod;
        }

        public void setSeedMethod(String seedMethod) {
            this.seedMethod = seedMethod;
        }

        public double getSeedPrice() {
            return seedPrice;
        }

        public void setSeedPrice(double seedPrice) {
            this.seedPrice = seedPrice;
        }

        public String getSeedManufacturer() {
            return seedManufacturer;
        }

        public void setSeedManufacturer(String seedManufacturer) {
            this.seedManufacturer = seedManufacturer;
        }

        public String getSeedNote() {
            return seedNote;
        }

        public void setSeedNote(String seedNote) {
            this.seedNote = seedNote;
        }

        public String getSeedStore() {
            return seedStore;
        }

        public void setSeedStore(String seedStore) {
            this.seedStore = seedStore;
        }

        public String getSeedPhone() {
            return seedPhone;
        }

        public void setSeedPhone(String seedPhone) {
            this.seedPhone = seedPhone;
        }

        public String getSeedProductiondate() {
            return seedProductiondate;
        }

        public void setSeedProductiondate(String seedProductiondate) {
            this.seedProductiondate = seedProductiondate;
        }

        public String getSeedShelflife() {
            return seedShelflife;
        }

        public void setSeedShelflife(String seedShelflife) {
            this.seedShelflife = seedShelflife;
        }

        public String getSeedPlantnumber() {
            return seedPlantnumber;
        }

        public void setSeedPlantnumber(String seedPlantnumber) {
            this.seedPlantnumber = seedPlantnumber;
        }

        public String getSeedType() {
            return seedType;
        }

        public void setSeedType(String seedType) {
            this.seedType = seedType;
        }

        public String getSeedImage() {
            return seedImage;
        }

        public void setSeedImage(String seedImage) {
            this.seedImage = seedImage;
        }
    }
}
