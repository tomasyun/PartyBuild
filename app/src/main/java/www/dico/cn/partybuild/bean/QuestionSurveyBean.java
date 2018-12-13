package www.dico.cn.partybuild.bean;

import java.util.List;

public class QuestionSurveyBean extends BaseProtocol {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 402880eb671a4cc701671a597ec90008
         * title : 问卷调查新增问答题测试IV
         * limitDate : 2018-11-16 11:00:00
         * participants : 1
         * isSubmit : 1
         */

        private String id;
        private String title;
        private String limitDate;
        private String participants;
        private String isSubmit;

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

        public String getLimitDate() {
            return limitDate;
        }

        public void setLimitDate(String limitDate) {
            this.limitDate = limitDate;
        }

        public String getParticipants() {
            return participants;
        }

        public void setParticipants(String participants) {
            this.participants = participants;
        }

        public String getIsSubmit() {
            return isSubmit;
        }

        public void setIsSubmit(String isSubmit) {
            this.isSubmit = isSubmit;
        }
    }
}
