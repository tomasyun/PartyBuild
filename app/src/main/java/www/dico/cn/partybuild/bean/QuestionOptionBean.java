package www.dico.cn.partybuild.bean;

import java.util.List;

public class QuestionOptionBean extends BaseProtocol {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String typeId;
        private String content;
        private String answer;
        private List<QuestionOptionsListBean> questionOptionsList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTypeId() {
            return typeId;
        }

        public void setTypeId(String typeId) {
            this.typeId = typeId;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

        public List<QuestionOptionsListBean> getQuestionOptionsList() {
            return questionOptionsList;
        }

        public void setQuestionOptionsList(List<QuestionOptionsListBean> questionOptionsList) {
            this.questionOptionsList = questionOptionsList;
        }

        public static class QuestionOptionsListBean {

            private String id;
            private String questionId;
            private String name;
            private String content;
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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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
