package com.beidou.domain;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lewis on 2018/6/13.
 */

public class AddFriend {

    /**
     * ret : {"action":"post","application":"0bb19700-657f-11e8-9127-51b997517a0c","path":"/messages","uri":"https://a1.easemob.com/1166180601253127/beidou/messages","data":{"15890143717":"success"},"timestamp":1528877538866,"duration":0,"organization":"1166180601253127","applicationName":"beidou"}
     * error : 0
     */
    private String reason;
    private RetBean ret;
    private int error;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

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
         * action : post
         * application : 0bb19700-657f-11e8-9127-51b997517a0c
         * path : /messages
         * uri : https://a1.easemob.com/1166180601253127/beidou/messages
         * data : {"15890143717":"success"}
         * timestamp : 1528877538866
         * duration : 0
         * organization : 1166180601253127
         * applicationName : beidou
         */

        private String action;
        private String application;
        private String path;
        private String uri;
        private DataBean data;
        private long timestamp;
        private int duration;
        private String organization;
        private String applicationName;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public String getApplication() {
            return application;
        }

        public void setApplication(String application) {
            this.application = application;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getUri() {
            return uri;
        }

        public void setUri(String uri) {
            this.uri = uri;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
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

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

        public String getApplicationName() {
            return applicationName;
        }

        public void setApplicationName(String applicationName) {
            this.applicationName = applicationName;
        }

        public static class DataBean {
            /**
             * 15890143717 : success
             */

            @SerializedName("15890143717")
            private String _$15890143717;

            public String get_$15890143717() {
                return _$15890143717;
            }

            public void set_$15890143717(String _$15890143717) {
                this._$15890143717 = _$15890143717;
            }
        }
    }
}
