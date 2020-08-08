package com.bigdata.agricultureplatform.specialist.activity.nongzi.bean;

import java.util.List;

/**
 * @description：$des$
 **/
public class MachineBean {

    /**
     * msg : 农机数据请求成功
     * agrimachineresult : [{"machineId":1,"machineName":"拖拉机","machineFunction":"犁地、旋耕","machineManufacturer":"宁夏","machinePhone":"158235523","machinePrice":50,"machineAddress":"怀远市场","machineType":"LF453","recommendData":"2020-07-23"},{"machineId":2,"machineName":"收割机","machineFunction":"收割水稻","machineManufacturer":"久保田","machinePhone":"152462525","machinePrice":80,"machineAddress":"怀远市场","machineType":"PR0688","recommendData":"2020-07-09"}]
     */

    private String msg;
    private List<AgrimachineresultBean> agrimachineresult;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<AgrimachineresultBean> getAgrimachineresult() {
        return agrimachineresult;
    }

    public void setAgrimachineresult(List<AgrimachineresultBean> agrimachineresult) {
        this.agrimachineresult = agrimachineresult;
    }

    public static class AgrimachineresultBean {
        private int machineId;
        private String machineName;
        private String machineFunction;
        private String machineManufacturer;
        private String machinePhone;
        private double machinePrice;
        private String machineAddress;
        private String machineType;
        private String recommendData;

        public int getMachineId() {
            return machineId;
        }

        public void setMachineId(int machineId) {
            this.machineId = machineId;
        }

        public String getMachineName() {
            return machineName;
        }

        public void setMachineName(String machineName) {
            this.machineName = machineName;
        }

        public String getMachineFunction() {
            return machineFunction;
        }

        public void setMachineFunction(String machineFunction) {
            this.machineFunction = machineFunction;
        }

        public String getMachineManufacturer() {
            return machineManufacturer;
        }

        public void setMachineManufacturer(String machineManufacturer) {
            this.machineManufacturer = machineManufacturer;
        }

        public String getMachinePhone() {
            return machinePhone;
        }

        public void setMachinePhone(String machinePhone) {
            this.machinePhone = machinePhone;
        }

        public double getMachinePrice() {
            return machinePrice;
        }

        public void setMachinePrice(double machinePrice) {
            this.machinePrice = machinePrice;
        }

        public String getMachineAddress() {
            return machineAddress;
        }

        public void setMachineAddress(String machineAddress) {
            this.machineAddress = machineAddress;
        }

        public String getMachineType() {
            return machineType;
        }

        public void setMachineType(String machineType) {
            this.machineType = machineType;
        }

        public String getRecommendData() {
            return recommendData;
        }

        public void setRecommendData(String recommendData) {
            this.recommendData = recommendData;
        }
    }
}
