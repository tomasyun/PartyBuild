package www.dico.cn.partybuild.bean;

import java.util.List;

public class ExamReviewBean extends BaseProtocol {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String id;
        private String examRuleId;
        private String examScore;
        private String isPass;
        private String examCost;
        private List<QuestionIdsBean> questionIds;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getExamRuleId() {
            return examRuleId;
        }

        public void setExamRuleId(String examRuleId) {
            this.examRuleId = examRuleId;
        }

        public String getExamScore() {
            return examScore;
        }

        public void setExamScore(String examScore) {
            this.examScore = examScore;
        }

        public String getIsPass() {
            return isPass;
        }

        public void setIsPass(String isPass) {
            this.isPass = isPass;
        }

        public String getExamCost() {
            return examCost;
        }

        public void setExamCost(String examCost) {
            this.examCost = examCost;
        }

        public List<QuestionIdsBean> getQuestionIds() {
            return questionIds;
        }

        public void setQuestionIds(List<QuestionIdsBean> questionIds) {
            this.questionIds = questionIds;
        }

        public static class QuestionIdsBean {

            private String id;
            private String questionId;
            private String isTrue;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getQuestionId() {
                return questionId;
            }

            public void setQuestionId(String questionId) {
                this.questionId = questionId;
            }

            public String getIsTrue() {
                return isTrue;
            }

            public void setIsTrue(String isTrue) {
                this.isTrue = isTrue;
            }
        }
    }
}
