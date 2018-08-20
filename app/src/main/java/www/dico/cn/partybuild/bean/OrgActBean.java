package www.dico.cn.partybuild.bean;

import java.util.List;

public class OrgActBean extends BaseProtocol {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String id;
        private String title;
        private String state;
        private String limitDate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getLimitDate() {
            return limitDate;
        }

        public void setLimitDate(String limitDate) {
            this.limitDate = limitDate;
        }
    }
}
