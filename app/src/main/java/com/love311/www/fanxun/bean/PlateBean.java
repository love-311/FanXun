package com.love311.www.fanxun.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/24.
 */
public class PlateBean {
    /**
     * msg : 查询成功
     * res : {"content":[{"id":32,"name":"市中心","pp":27,"temp":"涪城区"},{"id":33,"name":"南河","pp":27,"temp":"涪城区"},{"id":34,"name":"高水","pp":27,"temp":"涪城区"},{"id":35,"name":"花园","pp":27,"temp":"涪城区"},{"id":36,"name":"御营坝","pp":27,"temp":"涪城区"},{"id":37,"name":"开元场","pp":28,"temp":"游仙区"},{"id":38,"name":"沈家坝","pp":28,"temp":"游仙区"},{"id":41,"name":"游仙片区","pp":28,"temp":"游仙区"},{"id":52,"name":"石桥铺片区","pp":29,"temp":"高新区"},{"id":54,"name":"高新片区","pp":29,"temp":"高新区"},{"id":55,"name":"经开片区","pp":30,"temp":"经开区"},{"id":53,"name":"园艺山片区","pp":31,"temp":"科创园区"},{"id":57,"name":"西科大","pp":56,"temp":"青义区"}],"first":true,"last":true,"number":0,"numberOfElements":13,"size":15,"totalElements":13,"totalPages":1}
     * status : success
     */

    private String msg;
    /**
     * content : [{"id":32,"name":"市中心","pp":27,"temp":"涪城区"},{"id":33,"name":"南河","pp":27,"temp":"涪城区"},{"id":34,"name":"高水","pp":27,"temp":"涪城区"},{"id":35,"name":"花园","pp":27,"temp":"涪城区"},{"id":36,"name":"御营坝","pp":27,"temp":"涪城区"},{"id":37,"name":"开元场","pp":28,"temp":"游仙区"},{"id":38,"name":"沈家坝","pp":28,"temp":"游仙区"},{"id":41,"name":"游仙片区","pp":28,"temp":"游仙区"},{"id":52,"name":"石桥铺片区","pp":29,"temp":"高新区"},{"id":54,"name":"高新片区","pp":29,"temp":"高新区"},{"id":55,"name":"经开片区","pp":30,"temp":"经开区"},{"id":53,"name":"园艺山片区","pp":31,"temp":"科创园区"},{"id":57,"name":"西科大","pp":56,"temp":"青义区"}]
     * first : true
     * last : true
     * number : 0
     * numberOfElements : 13
     * size : 15
     * totalElements : 13
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
         * id : 32
         * name : 市中心
         * pp : 27
         * temp : 涪城区
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
            private int id;
            private String name;
            private int pp;
            private String temp;

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

            public int getPp() {
                return pp;
            }

            public void setPp(int pp) {
                this.pp = pp;
            }

            public String getTemp() {
                return temp;
            }

            public void setTemp(String temp) {
                this.temp = temp;
            }
        }
    }
}
