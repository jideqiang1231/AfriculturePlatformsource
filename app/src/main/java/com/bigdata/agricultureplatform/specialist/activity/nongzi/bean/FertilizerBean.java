package com.bigdata.agricultureplatform.specialist.activity.nongzi.bean;

import java.util.List;

/**
 * @description：$des$
 **/
public class FertilizerBean {

    /**
     * msg : 化肥数据请求成功
     * fertilizerServiceresult : [{"fertilizerId":1,"fertilizerName":"尿素","fertilizerType":1,"fertilizerN":"46%","fertilizerP":"0","fertilizerK":"0","fertilizerOther":"0","fertilizerInstructions":"追肥","fertilizerPrice":80,"fertilizerManufacturer":"宁夏化肥厂","fertilizerPhone":"1802255","fertilizerAddress":"怀远市场","fertilizerProductiondate":"2019-11-16","fertilizerShelflife":1,"fertilizerModeratecrop":"水稻","recommendData":"2020-07-17"},{"fertilizerId":2,"fertilizerName":"磷酸二铵","fertilizerType":1,"fertilizerN":"20.8%","fertilizerP":"52.8%","fertilizerK":"0","fertilizerOther":"0","fertilizerInstructions":"基肥","fertilizerPrice":80,"fertilizerManufacturer":"宁夏化肥厂","fertilizerPhone":"25255425","fertilizerAddress":"怀远市场","fertilizerProductiondate":"2019-11-16","fertilizerShelflife":2,"fertilizerModeratecrop":"水稻","recommendData":"2020-07-24"},{"fertilizerId":3,"fertilizerName":"复合肥","fertilizerType":1,"fertilizerN":"14%","fertilizerP":"16%","fertilizerK":"15%","fertilizerOther":"0","fertilizerInstructions":"基肥","fertilizerPrice":180,"fertilizerManufacturer":"山东红日阿康化工股份有限公司","fertilizerPhone":"1252425365","fertilizerAddress":"怀远市场","fertilizerProductiondate":"2019-11-16","fertilizerShelflife":3,"fertilizerModeratecrop":"水稻","recommendData":"2020-07-25"}]
     */

    private String msg;
    private List<FertilizerServiceresultBean> fertilizerServiceresult;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<FertilizerServiceresultBean> getFertilizerServiceresult() {
        return fertilizerServiceresult;
    }

    public void setFertilizerServiceresult(List<FertilizerServiceresultBean> fertilizerServiceresult) {
        this.fertilizerServiceresult = fertilizerServiceresult;
    }

    public static class FertilizerServiceresultBean {
        private int fertilizerId;
        private String fertilizerName;
        private int fertilizerType;
        private String fertilizerN;
        private String fertilizerP;
        private String fertilizerK;
        private String fertilizerOther;
        private String fertilizerInstructions;
        private double fertilizerPrice;
        private String fertilizerManufacturer;
        private String fertilizerPhone;
        private String fertilizerAddress;
        private String fertilizerProductiondate;
        private int fertilizerShelflife;
        private String fertilizerModeratecrop;
        private String recommendData;

        public int getFertilizerId() {
            return fertilizerId;
        }

        public void setFertilizerId(int fertilizerId) {
            this.fertilizerId = fertilizerId;
        }

        public String getFertilizerName() {
            return fertilizerName;
        }

        public void setFertilizerName(String fertilizerName) {
            this.fertilizerName = fertilizerName;
        }

        public int getFertilizerType() {
            return fertilizerType;
        }

        public void setFertilizerType(int fertilizerType) {
            this.fertilizerType = fertilizerType;
        }

        public String getFertilizerN() {
            return fertilizerN;
        }

        public void setFertilizerN(String fertilizerN) {
            this.fertilizerN = fertilizerN;
        }

        public String getFertilizerP() {
            return fertilizerP;
        }

        public void setFertilizerP(String fertilizerP) {
            this.fertilizerP = fertilizerP;
        }

        public String getFertilizerK() {
            return fertilizerK;
        }

        public void setFertilizerK(String fertilizerK) {
            this.fertilizerK = fertilizerK;
        }

        public String getFertilizerOther() {
            return fertilizerOther;
        }

        public void setFertilizerOther(String fertilizerOther) {
            this.fertilizerOther = fertilizerOther;
        }

        public String getFertilizerInstructions() {
            return fertilizerInstructions;
        }

        public void setFertilizerInstructions(String fertilizerInstructions) {
            this.fertilizerInstructions = fertilizerInstructions;
        }

        public double getFertilizerPrice() {
            return fertilizerPrice;
        }

        public void setFertilizerPrice(double fertilizerPrice) {
            this.fertilizerPrice = fertilizerPrice;
        }

        public String getFertilizerManufacturer() {
            return fertilizerManufacturer;
        }

        public void setFertilizerManufacturer(String fertilizerManufacturer) {
            this.fertilizerManufacturer = fertilizerManufacturer;
        }

        public String getFertilizerPhone() {
            return fertilizerPhone;
        }

        public void setFertilizerPhone(String fertilizerPhone) {
            this.fertilizerPhone = fertilizerPhone;
        }

        public String getFertilizerAddress() {
            return fertilizerAddress;
        }

        public void setFertilizerAddress(String fertilizerAddress) {
            this.fertilizerAddress = fertilizerAddress;
        }

        public String getFertilizerProductiondate() {
            return fertilizerProductiondate;
        }

        public void setFertilizerProductiondate(String fertilizerProductiondate) {
            this.fertilizerProductiondate = fertilizerProductiondate;
        }

        public int getFertilizerShelflife() {
            return fertilizerShelflife;
        }

        public void setFertilizerShelflife(int fertilizerShelflife) {
            this.fertilizerShelflife = fertilizerShelflife;
        }

        public String getFertilizerModeratecrop() {
            return fertilizerModeratecrop;
        }

        public void setFertilizerModeratecrop(String fertilizerModeratecrop) {
            this.fertilizerModeratecrop = fertilizerModeratecrop;
        }

        public String getRecommendData() {
            return recommendData;
        }

        public void setRecommendData(String recommendData) {
            this.recommendData = recommendData;
        }
    }
}
