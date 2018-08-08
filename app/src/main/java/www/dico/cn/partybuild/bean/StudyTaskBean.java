package www.dico.cn.partybuild.bean;

import java.util.List;

public class StudyTaskBean extends BaseProtocol{

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<Bean> studyTaskList;

        public List<Bean> getStudyTaskList() {
            return studyTaskList;
        }

        public void setStudyTaskList(List<Bean> studyTaskList) {
            this.studyTaskList = studyTaskList;
        }

        public static class  Bean {

            private String id;
            private String title;
            private String isElective;
            private String limitDate;
            private String totalHours;
            private String curHours;
            private String taskState;

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

            public String getIsElective() {
                return isElective;
            }

            public void setIsElective(String isElective) {
                this.isElective = isElective;
            }

            public String getLimitDate() {
                return limitDate;
            }

            public void setLimitDate(String limitDate) {
                this.limitDate = limitDate;
            }

            public String getTotalHours() {
                return totalHours;
            }

            public void setTotalHours(String totalHours) {
                this.totalHours = totalHours;
            }

            public String getCurHours() {
                return curHours;
            }

            public void setCurHours(String curHours) {
                this.curHours = curHours;
            }

            public String getTaskState() {
                return taskState;
            }

            public void setTaskState(String taskState) {
                this.taskState = taskState;
            }
        }
    }
}
