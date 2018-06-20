package com.beidou.domain;

import java.util.List;

/**
 * Created by lewis on 2018/6/14.
 */

public class CountryModel {


    /**
     * countryMsg : {"area":"亚洲","country":[{"name":"中国大陆","code":"0086"},{"name":"中国香港","code":"852"},{"name":"中国澳门","code":"853"},{"name":"日本","code":"81"},{"name":"韩国","code":"82"}]}
     * error : 0
     */

    private CountryMsgBean countryMsg;
    private int error;

    public CountryMsgBean getCountryMsg() {
        return countryMsg;
    }

    public void setCountryMsg(CountryMsgBean countryMsg) {
        this.countryMsg = countryMsg;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public static class CountryMsgBean {
        /**
         * area : 亚洲
         * country : [{"name":"中国大陆","code":"0086"},{"name":"中国香港","code":"852"},{"name":"中国澳门","code":"853"},{"name":"日本","code":"81"},{"name":"韩国","code":"82"}]
         */

        private String area;
        private List<CountryBean> country;

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public List<CountryBean> getCountry() {
            return country;
        }

        public void setCountry(List<CountryBean> country) {
            this.country = country;
        }

        public static class CountryBean {
            /**
             * name : 中国大陆
             * code : 0086
             */

            private String name;
            private String code;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}
