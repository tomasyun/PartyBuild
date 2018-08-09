package www.dico.cn.partybuild.bean;

import java.util.List;

public class ExamsBean extends BaseProtocol {

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
        private String examScore;
        private String limitDate;
        private String endDate;

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

        public String getExamScore() {
            return examScore;
        }

        public void setExamScore(String examScore) {
            this.examScore = examScore;
        }

        public String getLimitDate() {
            return limitDate;
        }

        public void setLimitDate(String limitDate) {
            this.limitDate = limitDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }
    }
}
