package com.love311.www.fanxun.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
public class SeePicBean {

    /**
     * msg : 查询成功
     * res : [{"url":"/wxfc/upload/2016/08/31/4ee7911aeeebb6b4a6b3c385da268332.jpg"},{"url":"/wxfc/upload/2016/08/31/fc90bbf8a9cee795d577d565eea55975.jpg"},{"url":"/wxfc/upload/2016/08/31/73bedcd02b471895ffe416dcbdb4d480.jpg"},{"url":"/wxfc/upload/2016/08/31/1134fb45e6bf104db16dc4c2fec8dab7.jpg"},{"url":"/wxfc/upload/2016/08/31/f815dfe3e6ca03be56b7bc4c62766cff.jpg"},{"url":"/wxfc/upload/2016/08/31/fa9feabd6b00eef95436bbc3b926425d.jpg"}]
     * status : success
     */

    private String msg;
    private String status;
    /**
     * url : /wxfc/upload/2016/08/31/4ee7911aeeebb6b4a6b3c385da268332.jpg
     */

    private List<ResBean> res;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResBean> getRes() {
        return res;
    }

    public void setRes(List<ResBean> res) {
        this.res = res;
    }

    public static class ResBean {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
