package www.dico.cn.partybuild.bean;

public class ExamResultBean extends BaseProtocol {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private int examScore;
        private String isPass;

        public int getExamScore() {
            return examScore;
        }

        public void setExamScore(int examScore) {
            this.examScore = examScore;
        }

        public String getIsPass() {
            return isPass;
        }

        public void setIsPass(String isPass) {
            this.isPass = isPass;
        }
    }
}
