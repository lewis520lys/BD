package com.beidou.domain;

import java.util.List;

/**
 * Created by lewis on 2018/6/13.
 */

public class FriendBean {

    /**
     * ret : {"action":"get","uri":"http://a1.easemob.com/1166180601253127/beidou/users/13213243717/contacts/users/","entities":[],"data":[],"timestamp":1528903319113,"duration":4,"count":0}
     * error : 0
     */
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    private RetBean ret;
    private int error;

    public RetBean getRet() {
        return ret;
    }

    public void setRet(RetBean ret) {
        this.ret = ret;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public static class RetBean {
        /**
         * action : get
         * uri : http://a1.easemob.com/1166180601253127/beidou/users/13213243717/contacts/users/
         * entities : []
         * data : []
         * timestamp : 1528903319113
         * duration : 4
         * count : 0
         */

        private String action;
        private String uri;
        private long timestamp;
        private int duration;
        private int count;
        private List<?> entities;
        private List<String> data;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<?> getEntities() {
            return entities;
        }

        public void setEntities(List<?> entities) {
            this.entities = entities;
        }

        public List<String> getData() {
            return data;
        }

        public void setData(List<String> data) {
            this.data = data;
        }
    }
}
