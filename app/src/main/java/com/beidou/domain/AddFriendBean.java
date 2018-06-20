package com.beidou.domain;

import java.util.List;

/**
 * Created by lewis on 2018/6/16.
 */

public class AddFriendBean {

    /**
     * ret : {"action":"post","application":"0bb19700-657f-11e8-9127-51b997517a0c","path":"/users/123a4460-70ff-11e8-a06b-6fc8a33d71b0/contacts","uri":"https://a1.easemob.com/1166180601253127/beidou/users/123a4460-70ff-11e8-a06b-6fc8a33d71b0/contacts","entities":[{"uuid":"2040ad90-6ed1-11e8-86f9-634798eecf98","type":"user","created":1528870510313,"modified":1529034637876,"username":"13213243717","activated":true,"nickname":"小王"}],"timestamp":1529110202045,"duration":63,"organization":"1166180601253127","applicationName":"beidou"}
     * error : 0
     */
    public String reason;
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
         * action : post
         * application : 0bb19700-657f-11e8-9127-51b997517a0c
         * path : /users/123a4460-70ff-11e8-a06b-6fc8a33d71b0/contacts
         * uri : https://a1.easemob.com/1166180601253127/beidou/users/123a4460-70ff-11e8-a06b-6fc8a33d71b0/contacts
         * entities : [{"uuid":"2040ad90-6ed1-11e8-86f9-634798eecf98","type":"user","created":1528870510313,"modified":1529034637876,"username":"13213243717","activated":true,"nickname":"小王"}]
         * timestamp : 1529110202045
         * duration : 63
         * organization : 1166180601253127
         * applicationName : beidou
         */

        private String action;
        private String application;
        private String path;
        private String uri;
        private long timestamp;
        private int duration;
        private String organization;
        private String applicationName;
        private List<EntitiesBean> entities;

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
             * modified : 1529034637876
             * username : 13213243717
             * activated : true
             * nickname : 小王
             */

            private String uuid;
            private String type;
            private long created;
            private long modified;
            private String username;
            private boolean activated;
            private String nickname;

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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }
        }
    }
}
