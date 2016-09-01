package com.love311.www.fanxun.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/29.
 */
public class SearchPassengerParameterBean {
    /**
     * msg : 查询成功
     * res : {"content":[{"address":"把","authstatus":"doing","chanQuan":"个人住宅","createDate":"2016-08-25 17:59:44","customerNature":"公客","customerSource":"门店客户","deleted":false,"floor":"22-22","housesPurpose":"科技","huXing":"3室2厅","id":782,"intoType":"立即入住","lastFurtherDate":"2016-08-25 17:59:44","lowPrice":22,"name":"22","phone":"把","proportion":"22-22","remarks":"","renovationInfo":"精装","shop":"时代店","status":"急需","tallPrice":22,"type":"二手房","user":"刘燕","username":"006"},{"address":"御营坝","authstatus":"doing","chanQuan":"个人住宅","createDate":"2016-07-25 18:19:18","customerNature":"公客","customerSource":"门店客户","deleted":false,"floor":"0-4","housesPurpose":"父母房子要拆迁，换房","huXing":"2室2厅","id":771,"idNumbwe":"","intoType":"立即入住","lastFurtherDate":"2016-07-25 18:19:21","lowPrice":0,"name":"杨姐","phone":"13350956325","proportion":"0-0","remarks":"","renovationInfo":"无要求","shop":"安昌店","status":"正常","tallPrice":30,"type":"二手房","user":"杨杰","username":"031","years":""},{"address":"13550809399","area":"花园","authstatus":"doing","chanQuan":"个人住宅","createDate":"2016-07-18 14:22:40","customerNature":"公客","customerSource":"门店客户","deleted":false,"floor":"0-0","housesPurpose":"住","huXing":"3室2厅","id":723,"idNumbwe":"","intoType":"立即入住","lastFurtherDate":"2016-07-18 14:22:40","lowPrice":0,"name":"姐","phone":"13550809399","proportion":"0-0","remarks":"","renovationInfo":"清水","shop":"南街店","status":"正常","tallPrice":50,"type":"二手房","user":"张芳","username":"038","years":""},{"address":"123","authstatus":"doing","chanQuan":"个人住宅","createDate":"2016-07-16 16:46:22","customerNature":"公客","customerSource":"其他","deleted":false,"floor":"0-0","housesPurpose":"","id":717,"idNumbwe":"","intoType":"立即入住","lastFurtherDate":"2016-07-20 18:09:02","lowPrice":0,"name":"陈哥","phone":"18142545182","proportion":"0-0","remarks":"香榭里大道","renovationInfo":"无要求","shop":"安昌店","status":"正常","tallPrice":0,"type":"二手房","user":"周鹏","username":"034","years":""},{"address":"三汇绿岛","area":"沈家坝","authstatus":"doing","chanQuan":"个人住宅","community":"三汇绿岛","createDate":"2016-07-16 13:59:17","customerNature":"公客","customerSource":"门店客户","deleted":false,"floor":"5-20","housesPurpose":"自住","huXing":"4室2厅","id":715,"idNumbwe":"","intoType":"立即入住","lastFurtherDate":"2016-07-16 13:59:17","lowPrice":80,"name":"杨哥","phone":"18981139256","proportion":"140-160","remarks":"","renovationInfo":"无要求","shop":"南街店","status":"正常","tallPrice":90,"type":"二手房","user":"王红江","username":"053","years":""},{"address":"水韵滨江","authstatus":"doing","chanQuan":"个人住宅","community":"水韵滨江","createDate":"2016-07-16 13:57:19","customerNature":"公客","customerSource":"其他","deleted":false,"floor":"0-0","housesPurpose":"","huXing":"4室2厅","id":714,"idNumbwe":"","intoType":"协商入住","lastFurtherDate":"2016-07-16 13:57:19","lowPrice":70,"name":"毛女士","phone":"18681677334","proportion":"140-170","remarks":"","renovationInfo":"清水","shop":"南街店","status":"正常","tallPrice":85,"type":"二手房","user":"王红江","username":"053","years":""},{"address":"永兴","area":"高新片区","authstatus":"doing","chanQuan":"个人住宅","createDate":"2016-07-16 09:43:31","customerNature":"公客","customerSource":"其他","deleted":true,"floor":"2-20","housesPurpose":"自住","huXing":"3室2厅","id":713,"idNumbwe":"","intoType":"立即入住","lastFurtherDate":"2016-07-18 16:47:47","lowPrice":40,"name":"任姐","phone":"15390266587","proportion":"90-110","remarks":"","renovationInfo":"中装","shop":"安昌店","status":"正常","tallPrice":50,"type":"二手房","user":"陈亮","username":"036","years":""},{"address":"绵阳","area":"石桥铺片区","authstatus":"doing","chanQuan":"个人住宅","createDate":"2016-07-16 09:35:18","customerNature":"公客","customerSource":"其他","deleted":true,"floor":"3-25","housesPurpose":"自住","huXing":"3室2厅","id":712,"idNumbwe":"","intoType":"立即入住","lastFurtherDate":"2016-07-17 11:46:39","lowPrice":40,"name":"王哥","phone":"13118170690","proportion":"75-105","remarks":"","renovationInfo":"清水","shop":"安昌店","status":"正常","tallPrice":55,"type":"二手房","user":"陈亮","username":"036","years":""},{"address":"南河坝","area":"南河","authstatus":"doing","chanQuan":"个人住宅","createDate":"2016-07-14 17:52:35","customerNature":"公客","customerSource":"门店客户","deleted":false,"floor":"0-0","housesPurpose":"自住","huXing":"3室2厅","id":704,"idNumbwe":"","intoType":"立即入住","lastFurtherDate":"2016-07-15 16:29:07","lowPrice":0,"name":"王君","phone":"13541766581","proportion":"0-130","remarks":"","renovationInfo":"无要求","shop":"安昌店","status":"正常","tallPrice":60,"type":"二手房","user":"杨杰","username":"031","years":""},{"address":"绵阳","area":"市中心","authstatus":"doing","chanQuan":"个人住宅","createDate":"2016-07-14 13:50:05","customerNature":"公客","customerSource":"其他","deleted":true,"floor":"2-20","housesPurpose":"自住","huXing":"3室2厅","id":702,"idNumbwe":"","intoType":"立即入住","lastFurtherDate":"2016-07-15 12:33:48","lowPrice":55,"name":"张哥","phone":"15281675368","proportion":"90-110","remarks":"","renovationInfo":"精装","shop":"安昌店","status":"正常","tallPrice":65,"type":"二手房","user":"陈亮","username":"036","years":""}],"first":true,"last":false,"number":0,"numberOfElements":10,"size":10,"sort":{},"totalElements":197,"totalPages":20}
     * status : success
     */

    private String msg;
    /**
     * content : [{"address":"把","authstatus":"doing","chanQuan":"个人住宅","createDate":"2016-08-25 17:59:44","customerNature":"公客","customerSource":"门店客户","deleted":false,"floor":"22-22","housesPurpose":"科技","huXing":"3室2厅","id":782,"intoType":"立即入住","lastFurtherDate":"2016-08-25 17:59:44","lowPrice":22,"name":"22","phone":"把","proportion":"22-22","remarks":"","renovationInfo":"精装","shop":"时代店","status":"急需","tallPrice":22,"type":"二手房","user":"刘燕","username":"006"},{"address":"御营坝","authstatus":"doing","chanQuan":"个人住宅","createDate":"2016-07-25 18:19:18","customerNature":"公客","customerSource":"门店客户","deleted":false,"floor":"0-4","housesPurpose":"父母房子要拆迁，换房","huXing":"2室2厅","id":771,"idNumbwe":"","intoType":"立即入住","lastFurtherDate":"2016-07-25 18:19:21","lowPrice":0,"name":"杨姐","phone":"13350956325","proportion":"0-0","remarks":"","renovationInfo":"无要求","shop":"安昌店","status":"正常","tallPrice":30,"type":"二手房","user":"杨杰","username":"031","years":""},{"address":"13550809399","area":"花园","authstatus":"doing","chanQuan":"个人住宅","createDate":"2016-07-18 14:22:40","customerNature":"公客","customerSource":"门店客户","deleted":false,"floor":"0-0","housesPurpose":"住","huXing":"3室2厅","id":723,"idNumbwe":"","intoType":"立即入住","lastFurtherDate":"2016-07-18 14:22:40","lowPrice":0,"name":"姐","phone":"13550809399","proportion":"0-0","remarks":"","renovationInfo":"清水","shop":"南街店","status":"正常","tallPrice":50,"type":"二手房","user":"张芳","username":"038","years":""},{"address":"123","authstatus":"doing","chanQuan":"个人住宅","createDate":"2016-07-16 16:46:22","customerNature":"公客","customerSource":"其他","deleted":false,"floor":"0-0","housesPurpose":"","id":717,"idNumbwe":"","intoType":"立即入住","lastFurtherDate":"2016-07-20 18:09:02","lowPrice":0,"name":"陈哥","phone":"18142545182","proportion":"0-0","remarks":"香榭里大道","renovationInfo":"无要求","shop":"安昌店","status":"正常","tallPrice":0,"type":"二手房","user":"周鹏","username":"034","years":""},{"address":"三汇绿岛","area":"沈家坝","authstatus":"doing","chanQuan":"个人住宅","community":"三汇绿岛","createDate":"2016-07-16 13:59:17","customerNature":"公客","customerSource":"门店客户","deleted":false,"floor":"5-20","housesPurpose":"自住","huXing":"4室2厅","id":715,"idNumbwe":"","intoType":"立即入住","lastFurtherDate":"2016-07-16 13:59:17","lowPrice":80,"name":"杨哥","phone":"18981139256","proportion":"140-160","remarks":"","renovationInfo":"无要求","shop":"南街店","status":"正常","tallPrice":90,"type":"二手房","user":"王红江","username":"053","years":""},{"address":"水韵滨江","authstatus":"doing","chanQuan":"个人住宅","community":"水韵滨江","createDate":"2016-07-16 13:57:19","customerNature":"公客","customerSource":"其他","deleted":false,"floor":"0-0","housesPurpose":"","huXing":"4室2厅","id":714,"idNumbwe":"","intoType":"协商入住","lastFurtherDate":"2016-07-16 13:57:19","lowPrice":70,"name":"毛女士","phone":"18681677334","proportion":"140-170","remarks":"","renovationInfo":"清水","shop":"南街店","status":"正常","tallPrice":85,"type":"二手房","user":"王红江","username":"053","years":""},{"address":"永兴","area":"高新片区","authstatus":"doing","chanQuan":"个人住宅","createDate":"2016-07-16 09:43:31","customerNature":"公客","customerSource":"其他","deleted":true,"floor":"2-20","housesPurpose":"自住","huXing":"3室2厅","id":713,"idNumbwe":"","intoType":"立即入住","lastFurtherDate":"2016-07-18 16:47:47","lowPrice":40,"name":"任姐","phone":"15390266587","proportion":"90-110","remarks":"","renovationInfo":"中装","shop":"安昌店","status":"正常","tallPrice":50,"type":"二手房","user":"陈亮","username":"036","years":""},{"address":"绵阳","area":"石桥铺片区","authstatus":"doing","chanQuan":"个人住宅","createDate":"2016-07-16 09:35:18","customerNature":"公客","customerSource":"其他","deleted":true,"floor":"3-25","housesPurpose":"自住","huXing":"3室2厅","id":712,"idNumbwe":"","intoType":"立即入住","lastFurtherDate":"2016-07-17 11:46:39","lowPrice":40,"name":"王哥","phone":"13118170690","proportion":"75-105","remarks":"","renovationInfo":"清水","shop":"安昌店","status":"正常","tallPrice":55,"type":"二手房","user":"陈亮","username":"036","years":""},{"address":"南河坝","area":"南河","authstatus":"doing","chanQuan":"个人住宅","createDate":"2016-07-14 17:52:35","customerNature":"公客","customerSource":"门店客户","deleted":false,"floor":"0-0","housesPurpose":"自住","huXing":"3室2厅","id":704,"idNumbwe":"","intoType":"立即入住","lastFurtherDate":"2016-07-15 16:29:07","lowPrice":0,"name":"王君","phone":"13541766581","proportion":"0-130","remarks":"","renovationInfo":"无要求","shop":"安昌店","status":"正常","tallPrice":60,"type":"二手房","user":"杨杰","username":"031","years":""},{"address":"绵阳","area":"市中心","authstatus":"doing","chanQuan":"个人住宅","createDate":"2016-07-14 13:50:05","customerNature":"公客","customerSource":"其他","deleted":true,"floor":"2-20","housesPurpose":"自住","huXing":"3室2厅","id":702,"idNumbwe":"","intoType":"立即入住","lastFurtherDate":"2016-07-15 12:33:48","lowPrice":55,"name":"张哥","phone":"15281675368","proportion":"90-110","remarks":"","renovationInfo":"精装","shop":"安昌店","status":"正常","tallPrice":65,"type":"二手房","user":"陈亮","username":"036","years":""}]
     * first : true
     * last : false
     * number : 0
     * numberOfElements : 10
     * size : 10
     * sort : {}
     * totalElements : 197
     * totalPages : 20
     */

    private ResBean res;
    private String status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResBean getRes() {
        return res;
    }

    public void setRes(ResBean res) {
        this.res = res;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResBean {
        private boolean first;
        private boolean last;
        private int number;
        private int numberOfElements;
        private int size;
        private int totalElements;
        private int totalPages;
        /**
         * address : 把
         * authstatus : doing
         * chanQuan : 个人住宅
         * createDate : 2016-08-25 17:59:44
         * customerNature : 公客
         * customerSource : 门店客户
         * deleted : false
         * floor : 22-22
         * housesPurpose : 科技
         * huXing : 3室2厅
         * id : 782
         * intoType : 立即入住
         * lastFurtherDate : 2016-08-25 17:59:44
         * lowPrice : 22
         * name : 22
         * phone : 把
         * proportion : 22-22
         * remarks :
         * renovationInfo : 精装
         * shop : 时代店
         * status : 急需
         * tallPrice : 22
         * type : 二手房
         * user : 刘燕
         * username : 006
         */

        private List<ContentBean> content;

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            private String address;
            private String authstatus;
            private String chanQuan;
            private String createDate;
            private String customerNature;
            private String customerSource;
            private boolean deleted;
            private String floor;
            private String housesPurpose;
            private String huXing;
            private int id;
            private String intoType;
            private String lastFurtherDate;
            private int lowPrice;
            private String name;
            private String phone;
            private String proportion;
            private String remarks;
            private String renovationInfo;
            private String shop;
            private String status;
            private int tallPrice;
            private String type;
            private String user;
            private String username;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getAuthstatus() {
                return authstatus;
            }

            public void setAuthstatus(String authstatus) {
                this.authstatus = authstatus;
            }

            public String getChanQuan() {
                return chanQuan;
            }

            public void setChanQuan(String chanQuan) {
                this.chanQuan = chanQuan;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public String getCustomerNature() {
                return customerNature;
            }

            public void setCustomerNature(String customerNature) {
                this.customerNature = customerNature;
            }

            public String getCustomerSource() {
                return customerSource;
            }

            public void setCustomerSource(String customerSource) {
                this.customerSource = customerSource;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }

            public String getFloor() {
                return floor;
            }

            public void setFloor(String floor) {
                this.floor = floor;
            }

            public String getHousesPurpose() {
                return housesPurpose;
            }

            public void setHousesPurpose(String housesPurpose) {
                this.housesPurpose = housesPurpose;
            }

            public String getHuXing() {
                return huXing;
            }

            public void setHuXing(String huXing) {
                this.huXing = huXing;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getIntoType() {
                return intoType;
            }

            public void setIntoType(String intoType) {
                this.intoType = intoType;
            }

            public String getLastFurtherDate() {
                return lastFurtherDate;
            }

            public void setLastFurtherDate(String lastFurtherDate) {
                this.lastFurtherDate = lastFurtherDate;
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

            public String getShop() {
                return shop;
            }

            public void setShop(String shop) {
                this.shop = shop;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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

            public String getUser() {
                return user;
            }

            public void setUser(String user) {
                this.user = user;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }
        }
    }
}
