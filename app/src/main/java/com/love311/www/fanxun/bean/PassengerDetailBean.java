package com.love311.www.fanxun.bean;

/**
 * Created by Administrator on 2016/8/26.
 */
public class PassengerDetailBean {

    /**
     * address : 把
     * customerNature : communal
     * customerNatureText : 公客
     * floor : 22-22
     * floorType : ZGCeng
     * floorTypeText : 中高层
     * housesPurpose : 科技
     * huXing : 13
     * huXingName : 3室2厅
     * id : 782
     * lowPrice : 22
     * name : 22
     * orientation : ss
     * orientationText : 南南
     * phone : 把
     * proportion : 22-22
     * remarks :
     * renovationInfo : H
     * renovationInfoText : 精装
     * status : jiXu
     * statusText : 急需
     * tallPrice : 22
     * type : oldHouses
     * typeText : 二手房
     */

    private MsgBean msg;
    /**
     * msg : {"address":"把","customerNature":"communal","customerNatureText":"公客","floor":"22-22","floorType":"ZGCeng","floorTypeText":"中高层","housesPurpose":"科技","huXing":13,"huXingName":"3室2厅","id":782,"lowPrice":22,"name":"22","orientation":"ss","orientationText":"南南","phone":"把","proportion":"22-22","remarks":"","renovationInfo":"H","renovationInfoText":"精装","status":"jiXu","statusText":"急需","tallPrice":22,"type":"oldHouses","typeText":"二手房"}
     * status : success
     */

    private String status;

    public MsgBean getMsg() {
        return msg;
    }

    public void setMsg(MsgBean msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class MsgBean {
        private String address;
        private String customerNature;
        private String customerNatureText;
        private String floor;
        private String floorType;
        private String floorTypeText;
        private String housesPurpose;
        private int huXing;
        private String huXingName;
        private int id;
        private int lowPrice;
        private String name;
        private String orientation;
        private String orientationText;
        private String phone;
        private String proportion;
        private String remarks;
        private String renovationInfo;
        private String renovationInfoText;
        private String status;
        private String statusText;
        private int tallPrice;
        private String type;
        private String typeText;
        private String rentTerm;

        public String getRentTermText() {
            return rentTermText;
        }

        public void setRentTermText(String rentTermText) {
            this.rentTermText = rentTermText;
        }

        private String rentTermText;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCustomerNature() {
            return customerNature;
        }

        public void setCustomerNature(String customerNature) {
            this.customerNature = customerNature;
        }

        public String getCustomerNatureText() {
            return customerNatureText;
        }

        public void setCustomerNatureText(String customerNatureText) {
            this.customerNatureText = customerNatureText;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getFloorType() {
            return floorType;
        }

        public void setFloorType(String floorType) {
            this.floorType = floorType;
        }

        public String getFloorTypeText() {
            return floorTypeText;
        }

        public void setFloorTypeText(String floorTypeText) {
            this.floorTypeText = floorTypeText;
        }

        public String getHousesPurpose() {
            return housesPurpose;
        }

        public void setHousesPurpose(String housesPurpose) {
            this.housesPurpose = housesPurpose;
        }

        public int getHuXing() {
            return huXing;
        }

        public void setHuXing(int huXing) {
            this.huXing = huXing;
        }

        public String getHuXingName() {
            return huXingName;
        }

        public void setHuXingName(String huXingName) {
            this.huXingName = huXingName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLowPrice() {
            return lowPrice;
        }

        public void setLowPrice(int lowPrice) {
            this.lowPrice = lowPrice;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrientation() {
            return orientation;
        }

        public void setOrientation(String orientation) {
            this.orientation = orientation;
        }

        public String getOrientationText() {
            return orientationText;
        }

        public void setOrientationText(String orientationText) {
            this.orientationText = orientationText;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getProportion() {
            return proportion;
        }

        public void setProportion(String proportion) {
            this.proportion = proportion;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getRenovationInfo() {
            return renovationInfo;
        }

        public void setRenovationInfo(String renovationInfo) {
            this.renovationInfo = renovationInfo;
        }

        public String getRenovationInfoText() {
            return renovationInfoText;
        }

        public void setRenovationInfoText(String renovationInfoText) {
            this.renovationInfoText = renovationInfoText;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusText() {
            return statusText;
        }

        public void setStatusText(String statusText) {
            this.statusText = statusText;
        }

        public int getTallPrice() {
            return tallPrice;
        }

        public void setTallPrice(int tallPrice) {
            this.tallPrice = tallPrice;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTypeText() {
            return typeText;
        }

        public void setTypeText(String typeText) {
            this.typeText = typeText;
        }
    }
}
