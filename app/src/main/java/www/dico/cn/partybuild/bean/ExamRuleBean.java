package www.dico.cn.partybuild.bean;

public class ExamRuleBean extends BaseProtocol{

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String id;
        private String title;
        private String totalScore;
        private String limitScore;
        private String examHours;
        private String questionNum;
        private String examDuration;

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

        public String getTotalScore() {
            return totalScore;
        }

        public void setTotalScore(String totalScore) {
            this.totalScore = totalScore;
        }

        public String getLimitScore() {
            return limitScore;
        }

        public void setLimitScore(String limitScore) {
            this.limitScore = limitScore;
        }

        public String getExamHours() {
            return examHours;
        }

        public void setExamHours(String examHours) {
            this.examHours = examHours;
        }

        public String getQuestionNum() {
            return questionNum;
        }

        public void setQuestionNum(String questionNum) {
            this.questionNum = questionNum;
        }

        public String getExamDuration() {
            return examDuration;
        }

        public void setExamDuration(String examDuration) {
            this.examDuration = examDuration;
        }
    }
}
