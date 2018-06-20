package com.beidou.domain;

/**
 * Created by lewis on 2018/6/14.
 */

public class ForgetPwdBean {

    /**
     * ret : {"action":"set user password","timestamp":1528983209663,"duration":10}
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
         * action : set user password
         * timestamp : 1528983209663
         * duration : 10
         */

        private String action;
        private long timestamp;
        private int duration;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
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
    }
}
