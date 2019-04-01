package www.dico.cn.partybuild.bean;

public class SignInBean extends BaseProtocol {

    /**
     * {"code":"0000","msg":"","data":{"id":"402880a1671a1a4c01671b9ba71a01df","theme":"安卓","themeImg":"/img/94d2187f024542d9889e113d68108e92.png","address":"陕西省西安市雁塔区丈八一路汇鑫IBC","startDate":"2018-11-16 19:01:00","type":"0"}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 402880a1671a1a4c01671b9ba71a01df
         * theme : 安卓
         * themeImg : /img/94d2187f024542d9889e113d68108e92.png
         * address : 陕西省西安市雁塔区丈八一路汇鑫IBC
         * startDate : 2018-11-16 19:01:00
         * type : 0
         */

        private String id;
        private String theme;
        private String themeImg;
        private String address;
        private String startDate;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getThemeImg() {
            return themeImg;
        }

        public void setThemeImg(String themeImg) {
            this.themeImg = themeImg;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
