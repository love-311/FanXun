package com.love311.www.fanxun.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/27.
 */
public class SearchParameterBean {
    /**
     * msg : 查询成功
     * res : {"content":[{"area":"市中心","community":"三光街7号","countFloor":6,"createDate":"2016-08-21 11:23:51","deleted":false,"entrustType":"网络委托","floor":3,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"3室2厅","id":5396,"isKan":"勘","keyInfo":"本公司","lastFurtherDate":"2016-08-22 15:40:49","name":"021","number":"005396","owner":"郑哥","proportion":70,"renovationInfo":"简装","salePrice":35,"shop":"南街店","soleName":"B1salesman","status":"pass","statusText":"正常","type":"二手房","userId":41,"userName":"李建"},{"area":"南河","community":"南郡公寓","countFloor":15,"createDate":"2016-08-18 11:17:12","deleted":false,"entrustType":"网络委托","floor":3,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"2室2厅","id":5284,"isKan":"勘","keyInfo":"无钥匙","lastFurtherDate":"2016-08-23 17:05:18","name":"021","number":"005284","owner":"汪叔","proportion":82.5,"renovationInfo":"精装","salePrice":52,"shop":"南街店","soleName":"B1salesman","status":"pass","statusText":"正常","type":"二手房","userId":41,"userName":"李建"},{"area":"高新片区","community":"金城世家","countFloor":6,"createDate":"2016-08-09 09:05:18","deleted":false,"entrustType":"网络委托","floor":4,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"3室2厅","id":4916,"isKan":"勘","keyInfo":"本公司","lastFurtherDate":"2016-08-23 17:18:45","name":"021","number":"004916","owner":"浦姐","proportion":139.49,"renovationInfo":"清水","salePrice":48,"shop":"南街店","soleName":"B1salesman","status":"pass","statusText":"正常","type":"二手房","userId":41,"userName":"李建"},{"area":"市中心","community":"富临·东方广场","countFloor":38,"createDate":"2016-07-18 16:52:43","deleted":false,"entrustType":"网络委托","floor":9,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"1室1厅","id":4145,"isKan":"勘","keyInfo":"本公司","lastFurtherDate":"2016-07-21 12:28:05","name":"011","number":"004145","owner":"张阿姨","proportion":60.04,"renovationInfo":"精装","salePrice":36,"shop":"东方店","soleName":"租赁部","status":"already","statusText":"已售","type":"二手房","userId":34,"userName":"杜志威"},{"area":"市中心","community":"金色时代","countFloor":24,"createDate":"2016-07-16 17:15:01","deleted":false,"entrustType":"网络委托","floor":24,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"1室1厅","id":4063,"isKan":" ","keyInfo":"无钥匙","lastFurtherDate":"2016-07-16 17:26:29","name":"012","number":"004063","owner":"哥","proportion":38,"renovationInfo":"精装","salePrice":22,"shop":"南街店","soleName":"B2salesman","status":"pass","statusText":"正常","type":"二手房","userId":37,"userName":"高陶"},{"area":"园艺山片区","community":"华润·中央公园","countFloor":33,"createDate":"2016-07-01 17:32:26","deleted":false,"entrustType":"网络委托","floor":7,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"4室2厅","id":3445,"isKan":"勘","keyInfo":"其他公司","lastFurtherDate":"2016-07-18 08:59:15","name":"034","number":"003445","owner":"双拥唐哥","proportion":128,"renovationInfo":"清水","salePrice":82,"shop":"安昌店","soleName":"internship_sale","status":"pass","statusText":"正常","type":"二手房","userId":54,"userName":"周鹏"},{"area":"高新片区","community":"塞纳阳光","countFloor":6,"createDate":"2016-07-01 17:21:37","deleted":false,"entrustType":"网络委托","floor":1,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"2室2厅","id":3444,"isKan":"勘","keyInfo":"无钥匙","lastFurtherDate":"2016-07-16 18:24:06","name":"021","number":"003444","owner":"唐姐","proportion":78.15,"renovationInfo":"清水","salePrice":42,"shop":"南街店","soleName":"B1salesman","status":"already","statusText":"已售","type":"二手房","userId":41,"userName":"李建"},{"area":"市中心","community":"时代大厦","countFloor":30,"createDate":"2016-06-04 12:08:58","deleted":true,"entrustType":"网络委托","floor":27,"floorPrice":0,"grade":"推荐房源","haveImg":false,"huXing":"1室1厅","id":2148,"isKan":" ","keyInfo":"无钥匙","lastFurtherDate":"2016-06-12 17:05:54","name":"032","number":"002148","owner":"钟姐","proportion":73,"renovationInfo":"精装","salePrice":36,"shop":"南街店","soleName":"internship_sale","status":"pass","statusText":"正常","type":"二手房","userId":53,"userName":"罗阳"},{"area":"高水","community":"富临·西蜀名居","countFloor":18,"createDate":"2016-05-21 16:23:41","deleted":true,"entrustType":"网络委托","floor":18,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"2室2厅","id":1497,"isKan":"勘","keyInfo":"无钥匙","lastFurtherDate":"2016-08-27 15:53:32","name":"014","number":"001497","owner":"娟姐","proportion":78.8,"renovationInfo":"精装","salePrice":39.8,"shop":"南街店","soleName":"gang boss","status":"pass","statusText":"正常","type":"二手房","userId":38,"userName":"杨丽"},{"area":"高水","community":"东方华尔街","countFloor":18,"createDate":"2016-05-06 10:39:26","deleted":true,"entrustType":"网络委托","floor":12,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"3室2厅","id":1017,"isKan":"勘","keyInfo":"其他公司","lastFurtherDate":"2016-08-18 15:07:42","name":"014","number":"001017","owner":"将女士","proportion":124,"renovationInfo":"清水","salePrice":65,"shop":"南街店","soleName":"gang boss","status":"pass","statusText":"正常","type":"二手房","userId":38,"userName":"杨丽"}],"first":true,"last":false,"number":0,"numberOfElements":10,"size":10,"sort":{},"totalElements":2661,"totalPages":267}
     * status : success
     */

    private String msg;
    /**
     * content : [{"area":"市中心","community":"三光街7号","countFloor":6,"createDate":"2016-08-21 11:23:51","deleted":false,"entrustType":"网络委托","floor":3,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"3室2厅","id":5396,"isKan":"勘","keyInfo":"本公司","lastFurtherDate":"2016-08-22 15:40:49","name":"021","number":"005396","owner":"郑哥","proportion":70,"renovationInfo":"简装","salePrice":35,"shop":"南街店","soleName":"B1salesman","status":"pass","statusText":"正常","type":"二手房","userId":41,"userName":"李建"},{"area":"南河","community":"南郡公寓","countFloor":15,"createDate":"2016-08-18 11:17:12","deleted":false,"entrustType":"网络委托","floor":3,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"2室2厅","id":5284,"isKan":"勘","keyInfo":"无钥匙","lastFurtherDate":"2016-08-23 17:05:18","name":"021","number":"005284","owner":"汪叔","proportion":82.5,"renovationInfo":"精装","salePrice":52,"shop":"南街店","soleName":"B1salesman","status":"pass","statusText":"正常","type":"二手房","userId":41,"userName":"李建"},{"area":"高新片区","community":"金城世家","countFloor":6,"createDate":"2016-08-09 09:05:18","deleted":false,"entrustType":"网络委托","floor":4,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"3室2厅","id":4916,"isKan":"勘","keyInfo":"本公司","lastFurtherDate":"2016-08-23 17:18:45","name":"021","number":"004916","owner":"浦姐","proportion":139.49,"renovationInfo":"清水","salePrice":48,"shop":"南街店","soleName":"B1salesman","status":"pass","statusText":"正常","type":"二手房","userId":41,"userName":"李建"},{"area":"市中心","community":"富临·东方广场","countFloor":38,"createDate":"2016-07-18 16:52:43","deleted":false,"entrustType":"网络委托","floor":9,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"1室1厅","id":4145,"isKan":"勘","keyInfo":"本公司","lastFurtherDate":"2016-07-21 12:28:05","name":"011","number":"004145","owner":"张阿姨","proportion":60.04,"renovationInfo":"精装","salePrice":36,"shop":"东方店","soleName":"租赁部","status":"already","statusText":"已售","type":"二手房","userId":34,"userName":"杜志威"},{"area":"市中心","community":"金色时代","countFloor":24,"createDate":"2016-07-16 17:15:01","deleted":false,"entrustType":"网络委托","floor":24,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"1室1厅","id":4063,"isKan":" ","keyInfo":"无钥匙","lastFurtherDate":"2016-07-16 17:26:29","name":"012","number":"004063","owner":"哥","proportion":38,"renovationInfo":"精装","salePrice":22,"shop":"南街店","soleName":"B2salesman","status":"pass","statusText":"正常","type":"二手房","userId":37,"userName":"高陶"},{"area":"园艺山片区","community":"华润·中央公园","countFloor":33,"createDate":"2016-07-01 17:32:26","deleted":false,"entrustType":"网络委托","floor":7,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"4室2厅","id":3445,"isKan":"勘","keyInfo":"其他公司","lastFurtherDate":"2016-07-18 08:59:15","name":"034","number":"003445","owner":"双拥唐哥","proportion":128,"renovationInfo":"清水","salePrice":82,"shop":"安昌店","soleName":"internship_sale","status":"pass","statusText":"正常","type":"二手房","userId":54,"userName":"周鹏"},{"area":"高新片区","community":"塞纳阳光","countFloor":6,"createDate":"2016-07-01 17:21:37","deleted":false,"entrustType":"网络委托","floor":1,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"2室2厅","id":3444,"isKan":"勘","keyInfo":"无钥匙","lastFurtherDate":"2016-07-16 18:24:06","name":"021","number":"003444","owner":"唐姐","proportion":78.15,"renovationInfo":"清水","salePrice":42,"shop":"南街店","soleName":"B1salesman","status":"already","statusText":"已售","type":"二手房","userId":41,"userName":"李建"},{"area":"市中心","community":"时代大厦","countFloor":30,"createDate":"2016-06-04 12:08:58","deleted":true,"entrustType":"网络委托","floor":27,"floorPrice":0,"grade":"推荐房源","haveImg":false,"huXing":"1室1厅","id":2148,"isKan":" ","keyInfo":"无钥匙","lastFurtherDate":"2016-06-12 17:05:54","name":"032","number":"002148","owner":"钟姐","proportion":73,"renovationInfo":"精装","salePrice":36,"shop":"南街店","soleName":"internship_sale","status":"pass","statusText":"正常","type":"二手房","userId":53,"userName":"罗阳"},{"area":"高水","community":"富临·西蜀名居","countFloor":18,"createDate":"2016-05-21 16:23:41","deleted":true,"entrustType":"网络委托","floor":18,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"2室2厅","id":1497,"isKan":"勘","keyInfo":"无钥匙","lastFurtherDate":"2016-08-27 15:53:32","name":"014","number":"001497","owner":"娟姐","proportion":78.8,"renovationInfo":"精装","salePrice":39.8,"shop":"南街店","soleName":"gang boss","status":"pass","statusText":"正常","type":"二手房","userId":38,"userName":"杨丽"},{"area":"高水","community":"东方华尔街","countFloor":18,"createDate":"2016-05-06 10:39:26","deleted":true,"entrustType":"网络委托","floor":12,"floorPrice":0,"grade":"推荐房源","haveImg":true,"huXing":"3室2厅","id":1017,"isKan":"勘","keyInfo":"其他公司","lastFurtherDate":"2016-08-18 15:07:42","name":"014","number":"001017","owner":"将女士","proportion":124,"renovationInfo":"清水","salePrice":65,"shop":"南街店","soleName":"gang boss","status":"pass","statusText":"正常","type":"二手房","userId":38,"userName":"杨丽"}]
     * first : true
     * last : false
     * number : 0
     * numberOfElements : 10
     * size : 10
     * sort : {}
     * totalElements : 2661
     * totalPages : 267
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
         * area : 市中心
         * community : 三光街7号
         * countFloor : 6
         * createDate : 2016-08-21 11:23:51
         * deleted : false
         * entrustType : 网络委托
         * floor : 3
         * floorPrice : 0
         * grade : 推荐房源
         * haveImg : true
         * huXing : 3室2厅
         * id : 5396
         * isKan : 勘
         * keyInfo : 本公司
         * lastFurtherDate : 2016-08-22 15:40:49
         * name : 021
         * number : 005396
         * owner : 郑哥
         * proportion : 70
         * renovationInfo : 简装
         * salePrice : 35
         * shop : 南街店
         * soleName : B1salesman
         * status : pass
         * statusText : 正常
         * type : 二手房
         * userId : 41
         * userName : 李建
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
            private String area;
            private String community;
            private int countFloor;
            private String createDate;
            private boolean deleted;
            private String entrustType;
            private int floor;
            private int floorPrice;
            private String grade;
            private boolean haveImg;
            private String huXing;
            private int id;
            private String isKan;
            private String keyInfo;
            private String lastFurtherDate;
            private String name;
            private String number;
            private String owner;
            private float proportion;
            private String renovationInfo;
            private float salePrice;
            private String shop;
            private String soleName;
            private String status;
            private String statusText;
            private String type;
            private int userId;
            private String userName;

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getCommunity() {
                return community;
            }

            public void setCommunity(String community) {
                this.community = community;
            }

            public int getCountFloor() {
                return countFloor;
            }

            public void setCountFloor(int countFloor) {
                this.countFloor = countFloor;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public boolean isDeleted() {
                return deleted;
            }

            public void setDeleted(boolean deleted) {
                this.deleted = deleted;
            }

            public String getEntrustType() {
                return entrustType;
            }

            public void setEntrustType(String entrustType) {
                this.entrustType = entrustType;
            }

            public int getFloor() {
                return floor;
            }

            public void setFloor(int floor) {
                this.floor = floor;
            }

            public int getFloorPrice() {
                return floorPrice;
            }

            public void setFloorPrice(int floorPrice) {
                this.floorPrice = floorPrice;
            }

            public String getGrade() {
                return grade;
            }

            public void setGrade(String grade) {
                this.grade = grade;
            }

            public boolean isHaveImg() {
                return haveImg;
            }

            public void setHaveImg(boolean haveImg) {
                this.haveImg = haveImg;
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

            public String getIsKan() {
                return isKan;
            }

            public void setIsKan(String isKan) {
                this.isKan = isKan;
            }

            public String getKeyInfo() {
                return keyInfo;
            }

            public void setKeyInfo(String keyInfo) {
                this.keyInfo = keyInfo;
            }

            public String getLastFurtherDate() {
                return lastFurtherDate;
            }

            public void setLastFurtherDate(String lastFurtherDate) {
                this.lastFurtherDate = lastFurtherDate;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getOwner() {
                return owner;
            }

            public void setOwner(String owner) {
                this.owner = owner;
            }

            public float getProportion() {
                return proportion;
            }

            public void setProportion(float proportion) {
                this.proportion = proportion;
            }

            public String getRenovationInfo() {
                return renovationInfo;
            }

            public void setRenovationInfo(String renovationInfo) {
                this.renovationInfo = renovationInfo;
            }

            public float getSalePrice() {
                return salePrice;
            }

            public void setSalePrice(float salePrice) {
                this.salePrice = salePrice;
            }

            public String getShop() {
                return shop;
            }

            public void setShop(String shop) {
                this.shop = shop;
            }

            public String getSoleName() {
                return soleName;
            }

            public void setSoleName(String soleName) {
                this.soleName = soleName;
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

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }
        }
    }
}
