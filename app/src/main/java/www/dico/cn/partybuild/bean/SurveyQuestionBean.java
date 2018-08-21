package www.dico.cn.partybuild.bean;

import java.util.List;

public class SurveyQuestionBean extends BaseProtocol {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {

        private String id;
        private String typeId;
        private String content;
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

        public List<QuestionOptionsListBean> getQuestionOptionsList() {
            return questionOptionsList;
        }

        public void setQuestionOptionsList(List<QuestionOptionsListBean> questionOptionsList) {
            this.questionOptionsList = questionOptionsList;
        }

        public static class QuestionOptionsListBean {

            private String id;
            private String name;
            private String content;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
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
        }
    }
}
