package com.love311.www.fanxun.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/8/24.
 */
public class BrokerBean {
    /**
     * msg : 查询成功
     * res : [{"id":0,"name":"员工不限"},{"createDate":"2016-03-29 10:56:32","id":31,"name":"蒋克华","shop":"时代店","status":"normal","username":"001"},{"createDate":"2016-03-29 11:00:33","id":33,"name":"马春雷","shop":"时代店","status":"normal","username":"003"},{"createDate":"2016-03-30 18:11:45","id":61,"name":"杨俊","shop":"时代店","status":"normal","username":"004"},{"createDate":"2016-03-30 18:13:23","id":62,"name":"张雪平","shop":"时代店","status":"normal","username":"005"},{"createDate":"2016-03-30 18:13:58","id":63,"name":"刘燕","shop":"时代店","status":"normal","username":"006"},{"createDate":"2016-04-25 09:34:40","id":69,"name":"赵静","shop":"时代店","status":"normal","username":"043"},{"createDate":"2016-05-05 14:26:46","id":79,"name":"公盘","shop":"时代店","status":"normal","username":"0000"},{"createDate":"2016-05-05 14:27:31","id":80,"name":"公盘","shop":"时代店","status":"normal","username":"000"},{"createDate":"2016-03-29 10:59:55","id":32,"name":"刘丹","shop":"南街店","status":"normal","username":"002"},{"createDate":"2016-03-29 11:02:34","id":35,"name":"张志强","shop":"南街店","status":"normal","username":"013"},{"createDate":"2016-03-29 11:50:54","id":37,"name":"高陶","shop":"南街店","status":"normal","username":"012"},{"createDate":"2016-03-29 11:54:55","id":41,"name":"李建","shop":"南街店","status":"normal","username":"021"},{"createDate":"2016-04-05 17:28:56","id":64,"name":"胡浩","shop":"南街店","status":"normal","username":"041"},{"createDate":"2016-04-19 11:13:41","id":67,"name":"刘跃","shop":"南街店","status":"normal","username":"042"},{"createDate":"2016-05-17 13:31:50","id":84,"name":"潘飞","shop":"南街店","status":"blocked","username":"045"},{"createDate":"2016-05-23 15:14:47","id":85,"name":"徐胜","shop":"南街店","status":"normal","username":"046"},{"createDate":"2016-06-21 18:11:21","id":89,"name":"李海","shop":"南街店","status":"blocked","username":"050"},{"createDate":"2016-07-04 16:24:44","id":92,"name":"王红江","shop":"南街店","status":"normal","username":"053"},{"createDate":"2016-07-12 14:27:16","id":95,"name":"刘志家","shop":"南街店","status":"blocked","username":"056"},{"createDate":"2016-08-08 16:21:19","id":103,"name":"张洪川","shop":"南街店","status":"normal","username":"064"},{"createDate":"2016-03-29 11:01:39","id":34,"name":"杜志威","shop":"东方店","status":"normal","username":"011"},{"createDate":"2016-03-29 11:03:34","id":36,"name":"李朝松","shop":"东方店","status":"normal","username":"018"},{"createDate":"2016-03-29 11:57:22","id":42,"name":"文丽丽","shop":"东方店","status":"normal","username":"016"},{"createDate":"2016-03-29 11:58:50","id":43,"name":"陈鑫","shop":"东方店","status":"normal","username":"017"},{"createDate":"2016-03-30 17:53:18","id":46,"name":"刘羊","shop":"东方店","status":"normal","username":"024"},{"createDate":"2016-03-30 17:56:11","id":47,"name":"王羽","shop":"东方店","status":"normal","username":"025"},{"createDate":"2016-03-30 18:01:06","id":51,"name":"刘雪琴","shop":"东方店","status":"normal","username":"030"},{"createDate":"2016-03-30 18:07:47","id":57,"name":"王鹏","shop":"东方店","status":"normal","username":"037"},{"createDate":"2016-07-08 15:46:49","id":93,"name":"曾小虎","shop":"东方店","status":"normal","username":"054"},{"createDate":"2016-07-08 16:28:31","id":94,"name":"杨力","shop":"东方店","status":"blocked","username":"055"},{"createDate":"2016-07-14 15:45:41","id":98,"name":"米雪冬","shop":"东方店","status":"normal","username":"059"},{"createDate":"2016-07-14 17:20:22","id":100,"name":"陈思佳","shop":"东方店","status":"normal","username":"061"},{"createDate":"2016-08-03 10:54:30","id":102,"name":"蒋进","shop":"东方店","status":"normal","username":"063"},{"createDate":"2016-08-14 17:51:26","id":104,"name":"高阳","shop":"东方店","status":"normal","username":"065"},{"createDate":"2016-08-20 09:05:36","id":105,"name":" 彭白林","shop":"东方店","status":"normal","username":"066"},{"createDate":"2016-03-29 11:53:05","id":39,"name":"何良兵","shop":"安昌店","status":"normal","username":"015"},{"createDate":"2016-03-30 17:59:05","id":49,"name":"刘仕海","shop":"安昌店","status":"normal","username":"027"},{"createDate":"2016-03-30 18:02:13","id":52,"name":"杨杰","shop":"安昌店","status":"normal","username":"031"},{"createDate":"2016-03-30 18:04:21","id":54,"name":"周鹏","shop":"安昌店","status":"normal","username":"034"}]
     * status : success
     */

    private String msg;
    private String status;
    /**
     * id : 0
     * name : 员工不限
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
        private int id;
        private String name;

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
