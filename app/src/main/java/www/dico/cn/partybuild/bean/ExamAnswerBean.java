package www.dico.cn.partybuild.bean;

import java.util.List;

public class ExamAnswerBean {
    //{"examRecord":{"examRuleId":"","examTime":""},"testAnswers":[{"questionId":"","answer":"B"}],"limitScore":""}
    private ExamRecordBean examRecord;
    private List<TestAnswersBean> testAnswers;
    private String limitScore;

    public ExamRecordBean getExamRecord() {
        return examRecord;
    }

    public void setExamRecord(ExamRecordBean examRecord) {
        this.examRecord = examRecord;
    }

    public List<TestAnswersBean> getTestAnswers() {
        return testAnswers;
    }

    public void setTestAnswers(List<TestAnswersBean> testAnswers) {
        this.testAnswers = testAnswers;
    }

    public String getLimitScore() {
        return limitScore;
    }

    public void setLimitScore(String limitScore) {
        this.limitScore = limitScore;
    }

    public static class ExamRecordBean {

        private String examRuleId;
        private String examTime;
        private String examCost;

        public String getExamCost() {
            return examCost;
        }

        public void setExamCost(String examCost) {
            this.examCost = examCost;
        }

        public String getExamRuleId() {
            return examRuleId;
        }

        public void setExamRuleId(String examRuleId) {
            this.examRuleId = examRuleId;
        }

        public String getExamTime() {
            return examTime;
        }

        public void setExamTime(String examTime) {
            this.examTime = examTime;
        }
    }

    public static class TestAnswersBean {

        private String questionId;
        private String answer;

        public String getQuestionId() {
            return questionId;
        }

        public void setQuestionId(String questionId) {
            this.questionId = questionId;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
