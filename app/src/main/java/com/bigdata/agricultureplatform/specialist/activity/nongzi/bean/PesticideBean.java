package com.bigdata.agricultureplatform.specialist.activity.nongzi.bean;

import java.util.List;

/**
 * @description：$des$
 **/
public class PesticideBean {
    /**
     * msg : 农药数据请求成功
     * pesticidesresult : [{"pesticideId":1,"pesticideName":"稻杰","pesticideComposition":"五氟磺草胺","pesticideDosage":"乳油","pesticideContent":"25克/L","pesticidePredisease":"防治稗草、阔叶草、莎草等主要杂草","pesticideInstructions":"喷雾 40～80毫升/亩，采用喷雾法施药。","pesticidePrice":80,"pesticideManufacturer":"PD20170671","pesticideLicensenumber":"陶氏化学公司","pesticideAddress":"2019-11-16 21:21:36","pesticideProductiondate":"2020-03-20","pesticideModeratecrop":"水稻 ","pesticideVirulence":"内吸光谱性","pesticideNote":"低毒","pesticidePhone":"123456","pesticideShelflife":2,"recommendData":"2020-07-16"}]
     */

    private String msg;
    private List<PesticidesresultBean> pesticidesresult;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<PesticidesresultBean> getPesticidesresult() {
        return pesticidesresult;
    }

    public void setPesticidesresult(List<PesticidesresultBean> pesticidesresult) {
        this.pesticidesresult = pesticidesresult;
    }

    public static class PesticidesresultBean {
        private int pesticideId;
        private String pesticideName;
        private String pesticideComposition;
        private String pesticideDosage;
        private String pesticideContent;
        private String pesticidePredisease;
        private String pesticideInstructions;
        private double pesticidePrice;
        private String pesticideManufacturer;
        private String pesticideLicensenumber;
        private String pesticideAddress;
        private String pesticideProductiondate;
        private String pesticideModeratecrop;
        private String pesticideVirulence;
        private String pesticideNote;
        private String pesticidePhone;
        private int pesticideShelflife;
        private String recommendData;

        public int getPesticideId() {
            return pesticideId;
        }

        public void setPesticideId(int pesticideId) {
            this.pesticideId = pesticideId;
        }

        public String getPesticideName() {
            return pesticideName;
        }

        public void setPesticideName(String pesticideName) {
            this.pesticideName = pesticideName;
        }

        public String getPesticideComposition() {
            return pesticideComposition;
        }

        public void setPesticideComposition(String pesticideComposition) {
            this.pesticideComposition = pesticideComposition;
        }

        public String getPesticideDosage() {
            return pesticideDosage;
        }

        public void setPesticideDosage(String pesticideDosage) {
            this.pesticideDosage = pesticideDosage;
        }

        public String getPesticideContent() {
            return pesticideContent;
        }

        public void setPesticideContent(String pesticideContent) {
            this.pesticideContent = pesticideContent;
        }

        public String getPesticidePredisease() {
            return pesticidePredisease;
        }

        public void setPesticidePredisease(String pesticidePredisease) {
            this.pesticidePredisease = pesticidePredisease;
        }

        public String getPesticideInstructions() {
            return pesticideInstructions;
        }

        public void setPesticideInstructions(String pesticideInstructions) {
            this.pesticideInstructions = pesticideInstructions;
        }

        public double getPesticidePrice() {
            return pesticidePrice;
        }

        public void setPesticidePrice(double pesticidePrice) {
            this.pesticidePrice = pesticidePrice;
        }

        public String getPesticideManufacturer() {
            return pesticideManufacturer;
        }

        public void setPesticideManufacturer(String pesticideManufacturer) {
            this.pesticideManufacturer = pesticideManufacturer;
        }

        public String getPesticideLicensenumber() {
            return pesticideLicensenumber;
        }

        public void setPesticideLicensenumber(String pesticideLicensenumber) {
            this.pesticideLicensenumber = pesticideLicensenumber;
        }

        public String getPesticideAddress() {
            return pesticideAddress;
        }

        public void setPesticideAddress(String pesticideAddress) {
            this.pesticideAddress = pesticideAddress;
        }

        public String getPesticideProductiondate() {
            return pesticideProductiondate;
        }

        public void setPesticideProductiondate(String pesticideProductiondate) {
            this.pesticideProductiondate = pesticideProductiondate;
        }

        public String getPesticideModeratecrop() {
            return pesticideModeratecrop;
        }

        public void setPesticideModeratecrop(String pesticideModeratecrop) {
            this.pesticideModeratecrop = pesticideModeratecrop;
        }

        public String getPesticideVirulence() {
            return pesticideVirulence;
        }

        public void setPesticideVirulence(String pesticideVirulence) {
            this.pesticideVirulence = pesticideVirulence;
        }

        public String getPesticideNote() {
            return pesticideNote;
        }

        public void setPesticideNote(String pesticideNote) {
            this.pesticideNote = pesticideNote;
        }

        public String getPesticidePhone() {
            return pesticidePhone;
        }

        public void setPesticidePhone(String pesticidePhone) {
            this.pesticidePhone = pesticidePhone;
        }

        public int getPesticideShelflife() {
            return pesticideShelflife;
        }

        public void setPesticideShelflife(int pesticideShelflife) {
            this.pesticideShelflife = pesticideShelflife;
        }

        public String getRecommendData() {
            return recommendData;
        }

        public void setRecommendData(String recommendData) {
            this.recommendData = recommendData;
        }
    }
}
