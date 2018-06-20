package com.beidou.domain;

import java.util.List;

/**
 * Created by lewis on 2018/6/15.
 */

public class AreaModel {

    /**
     * areaMsg : [{"area":"亚洲"},{"area":"欧洲"},{"area":"北美洲"},{"area":"南美洲"},{"area":"大洋洲"},{"area":"非洲"}]
     * error : 0
     */

    private int error;
    private List<AreaMsgBean> areaMsg;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<AreaMsgBean> getAreaMsg() {
        return areaMsg;
    }

    public void setAreaMsg(List<AreaMsgBean> areaMsg) {
        this.areaMsg = areaMsg;
    }

    public static class AreaMsgBean {
        /**
         * area : 亚洲
         */

        private String area;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }
    }
}
