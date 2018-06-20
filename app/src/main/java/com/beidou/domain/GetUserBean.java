package com.beidou.domain;

import java.util.List;

/**
 * Created by lewis on 2018/6/13.
 */

public class GetUserBean {

    /**
     * ret : {"action":"get","path":"/users","uri":"http://a1.easemob.com/1166180601253127/beidou/users/13213243717","entities":[{"uuid":"2040ad90-6ed1-11e8-86f9-634798eecf98","type":"user","created":1528870510313,"modified":1528870510313,"username":"13213243717","activated":true}],"timestamp":1528876068485,"duration":1,"count":1}
     * error : 0
     */

    private RetBean ret;
    private int error;
    private String reason;

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
         * action : get
         * path : /users
         * uri : http://a1.easemob.com/1166180601253127/beidou/users/13213243717
         * entities : [{"uuid":"2040ad90-6ed1-11e8-86f9-634798eecf98","type":"user","created":1528870510313,"modified":1528870510313,"username":"13213243717","activated":true}]
         * timestamp : 1528876068485
         * duration : 1
         * count : 1
         */

        private String action;
        private String path;
        private String uri;
        private long timestamp;
        private int duration;
        private int count;
        private List<EntitiesBean> entities;

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
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

        public List<EntitiesBean> getEntities() {
            return entities;
        }

        public void setEntities(List<EntitiesBean> entities) {
            this.entities = entities;
        }

        public static class EntitiesBean {
            /**
             * uuid : 2040ad90-6ed1-11e8-86f9-634798eecf98
             * type : user
             * created : 1528870510313
             * modified : 1528870510313
             * username : 13213243717
             * activated : true
             */

            private String uuid;
            private String type;
            private long created;
            private long modified;
            private String username;
            private boolean activated;

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public long getCreated() {
                return created;
            }

            public void setCreated(long created) {
                this.created = created;
            }

            public long getModified() {
                return modified;
            }

            public void setModified(long modified) {
                this.modified = modified;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public boolean isActivated() {
                return activated;
            }

            public void setActivated(boolean activated) {
                this.activated = activated;
            }
        }
    }
}
