package www.dico.cn.partybuild.bean;

public class CourseInfoBean extends BaseProtocol {
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
        private String context;
        private double courseHours;
        private String hoursOk;

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

        public String getContext() {
            return context;
        }

        public void setContext(String context) {
            this.context = context;
        }

        public double getCourseHours() {
            return courseHours;
        }

        public void setCourseHours(double courseHours) {
            this.courseHours = courseHours;
        }

        public String getHoursOk() {
            return hoursOk;
        }

        public void setHoursOk(String hoursOk) {
            this.hoursOk = hoursOk;
        }
    }
}
