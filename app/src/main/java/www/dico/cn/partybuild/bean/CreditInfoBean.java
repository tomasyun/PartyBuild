package www.dico.cn.partybuild.bean;

import java.util.List;

public class CreditInfoBean extends BaseProtocol {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String totalScore;
        private List<CreditListBean> creditList;

        public String getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(String totalScore) {
            this.totalScore = totalScore;
        }

        public List<CreditListBean> getCreditList() {
            return creditList;
        }

        public void setCreditList(List<CreditListBean> creditList) {
            this.creditList = creditList;
        }

        public static class CreditListBean {
            private String id;
            private String title;
            private String score;
            private String auditDate;

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

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getAuditDate() {
                return auditDate;
            }

            public void setAuditDate(String auditDate) {
                this.auditDate = auditDate;
            }
        }
    }
}
