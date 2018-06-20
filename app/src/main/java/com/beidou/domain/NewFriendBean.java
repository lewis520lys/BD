package com.beidou.domain;

import java.util.List;

/**
 * Created by lewis on 2018/6/15.
 */

public class NewFriendBean {

    /**
     * error : 0
     * list : [{"id":8,"ownerMobie":"13213243717","othersMobie":null,"status":"0"}]
     */

    private int error;
    private List<ListBean> list;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 8
         * ownerMobie : 13213243717
         * othersMobie : null
         * status : 0
         */

        private int id;
        private String ownerMobie;
        private Object othersMobie;
        private String status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOwnerMobie() {
            return ownerMobie;
        }

        public void setOwnerMobie(String ownerMobie) {
            this.ownerMobie = ownerMobie;
        }

        public Object getOthersMobie() {
            return othersMobie;
        }

        public void setOthersMobie(Object othersMobie) {
            this.othersMobie = othersMobie;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
