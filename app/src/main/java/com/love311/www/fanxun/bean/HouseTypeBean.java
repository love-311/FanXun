package com.love311.www.fanxun.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
public class HouseTypeBean {
    /**
     * msg : 查询成功
     * res : {"content":[{"createDate":"2016-04-25 13:45:03","id":16,"name":"其他","new":false},{"createDate":"2016-04-13 13:11:03","id":15,"name":"5室2厅","new":false},{"createDate":"2016-04-05 12:59:39","id":14,"name":"4室2厅","new":false},{"createDate":"2016-04-05 12:58:37","id":13,"name":"3室2厅","new":false},{"createDate":"2016-04-05 12:58:22","id":12,"name":"2室2厅","new":false},{"createDate":"2016-04-05 12:58:12","id":11,"name":"1室1厅","new":false}],"first":true,"last":true,"number":0,"numberOfElements":6,"size":15,"sort":{},"totalElements":6,"totalPages":1}
     * status : success
     */

    private String msg;
    /**
     * content : [{"createDate":"2016-04-25 13:45:03","id":16,"name":"其他","new":false},{"createDate":"2016-04-13 13:11:03","id":15,"name":"5室2厅","new":false},{"createDate":"2016-04-05 12:59:39","id":14,"name":"4室2厅","new":false},{"createDate":"2016-04-05 12:58:37","id":13,"name":"3室2厅","new":false},{"createDate":"2016-04-05 12:58:22","id":12,"name":"2室2厅","new":false},{"createDate":"2016-04-05 12:58:12","id":11,"name":"1室1厅","new":false}]
     * first : true
     * last : true
     * number : 0
     * numberOfElements : 6
     * size : 15
     * sort : {}
     * totalElements : 6
     * totalPages : 1
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
         * createDate : 2016-04-25 13:45:03
         * id : 16
         * name : 其他
         * new : false
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
            private String createDate;
            private int id;
            private String name;
            @SerializedName("new")
            private boolean newX;

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public boolean isNewX() {
                return newX;
            }

            public void setNewX(boolean newX) {
                this.newX = newX;
            }
        }
    }
}
