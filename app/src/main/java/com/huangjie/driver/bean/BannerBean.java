package com.huangjie.driver.bean;

import java.util.List;


/**
 * Created by huangjie on 2017/1/20.
 */

public class BannerBean {
    private int version;
    private List<BannerItem> data;

    public int getVersion() {
        return version;
    }

    public List<BannerItem> getData() {
        return data;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public void setData(List<BannerItem> data) {
        this.data = data;
    }

    public static class BannerItem {
        private int id;
        private String name;
        private String desc;
        private int style;
        private int isNameDisplay;
        private ActionBean action;
        private List<DataItem> mList;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

        public int getStyle() {
            return style;
        }

        public int getIsNameDisplay() {
            return isNameDisplay;
        }

        public ActionBean getAction() {
            return action;
        }

        public List<DataItem> getmList() {
            return mList;
        }
    }

    private static class ActionBean {
        private int type;

        public int getType() {
            return type;
        }
    }

    public static class DataItem {
        private int id;
        private String name;
        private String desc;
        private String picUrl;
        private ActionX action;
        /**
         * id : 3926
         * name : 长大的童话
         * desc : 林宥嘉
         * picUrl : http://pic.xiami.net/images/trade/ams_homepage/195/58370f164f6dc_9754208_1480003350.jpg
         * action : {"type":19,"value":"2102656296"}
         * albumRightKey : {"buyFlag":false,"price":0,"vipFree":null,"quality":null,"active":null,"count":-1,"buy":false,"dmsg":""}
         * status : 1
         * isExclusive : 1
         */
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getDesc() {
            return desc;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public ActionX getAction() {
            return action;
        }
    }

    public static class ActionX {
        private int type;
        private String value;

    }

}
