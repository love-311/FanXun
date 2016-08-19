package com.love311.www.fanxun.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/18.
 */
public class SearchBean {
    /**
     * msg : 查询成功
     * res : {"content":[{"area":"沈家坝","areaId":38,"createDate":"2016-05-02 10:49:12","id":404,"name":"芙蓉小区"},{"area":"开元场","areaId":37,"createDate":"2016-04-13 09:55:20","id":181,"name":"红芙蓉小区"}],"first":true,"last":true,"number":0,"numberOfElements":2,"size":15,"sort":{},"totalElements":2,"totalPages":1}
     * status : success
     */

    private String msg;
    /**
     * content : [{"area":"沈家坝","areaId":38,"createDate":"2016-05-02 10:49:12","id":404,"name":"芙蓉小区"},{"area":"开元场","areaId":37,"createDate":"2016-04-13 09:55:20","id":181,"name":"红芙蓉小区"}]
     * first : true
     * last : true
     * number : 0
     * numberOfElements : 2
     * size : 15
     * sort : {}
     * totalElements : 2
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
         * area : 沈家坝
         * areaId : 38
         * createDate : 2016-05-02 10:49:12
         * id : 404
         * name : 芙蓉小区
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
            private int areaId;
            private String createDate;
            private int id;
            private String name;

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public int getAreaId() {
                return areaId;
            }

            public void setAreaId(int areaId) {
                this.areaId = areaId;
            }

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
        }
    }
}
