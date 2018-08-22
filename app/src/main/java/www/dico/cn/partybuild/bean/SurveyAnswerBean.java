package www.dico.cn.partybuild.bean;

import java.util.List;

public class SurveyAnswerBean {
    /**
     * id : 问卷id
     * answers : [{"userId":"放问题id","answer":""}]
     */

    private String id;
    private List<AnswersBean> answers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<AnswersBean> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersBean> answers) {
        this.answers = answers;
    }

    public static class AnswersBean {
        private String userId;
        private String answer;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
