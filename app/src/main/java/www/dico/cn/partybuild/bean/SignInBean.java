package www.dico.cn.partybuild.bean;

public class SignInBean extends BaseProtocol {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String theme;
        private String themeImg;
        private String address;
        private String startDate;
        private String curDate;
        private String is;

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

        public String getCurDate() {
            return curDate;
        }

        public void setCurDate(String curDate) {
            this.curDate = curDate;
        }

        public String getIs() {
            return is;
        }

        public void setIs(String is) {
            this.is = is;
        }
    }
}
