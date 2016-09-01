package com.love311.www.fanxun.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/8/31.
 */
public class MyFragmentBean {
    /**
     * msg : 获取成功
     * res : {"createDate":"2016-03-30 18:13:58","deleted":false,"displayOrganizationJobs":{},"headImg":"/wxfc/upload/2016/04/21/01e637416be1acdcfa6a08fb1a411436.JPG","id":63,"isWorker":true,"name":"刘燕","new":false,"nickName":"wboc9d63","organizationJobs":[],"password":"754031e0f7702fa3b4a21fa951e7f6ac","phone":"13881159677","salt":"GAycobSupa","source":"后台添加","status":"normal","username":"006"}
     * status : success
     */

    private String msg;
    /**
     * createDate : 2016-03-30 18:13:58
     * deleted : false
     * displayOrganizationJobs : {}
     * headImg : /wxfc/upload/2016/04/21/01e637416be1acdcfa6a08fb1a411436.JPG
     * id : 63
     * isWorker : true
     * name : 刘燕
     * new : false
     * nickName : wboc9d63
     * organizationJobs : []
     * password : 754031e0f7702fa3b4a21fa951e7f6ac
     * phone : 13881159677
     * salt : GAycobSupa
     * source : 后台添加
     * status : normal
     * username : 006
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
        private String createDate;
        private boolean deleted;
        private String headImg;
        private int id;
        private boolean isWorker;
        private String name;
        @SerializedName("new")
        private boolean newX;
        private String nickName;
        private String password;
        private String phone;
        private String salt;
        private String source;
        private String status;
        private String username;
        private List<?> organizationJobs;

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

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIsWorker() {
            return isWorker;
        }

        public void setIsWorker(boolean isWorker) {
            this.isWorker = isWorker;
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

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getSalt() {
            return salt;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<?> getOrganizationJobs() {
            return organizationJobs;
        }

        public void setOrganizationJobs(List<?> organizationJobs) {
            this.organizationJobs = organizationJobs;
        }
    }
}
