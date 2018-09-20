package www.dico.cn.partybuild.bean;


public class MeetSummaryBean extends BaseProtocol {
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
        private String category;
        private String speaker;
        private String endDate;
        private String summary;
        private String attachment;

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

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getSpeaker() {
            return speaker;
        }

        public void setSpeaker(String speaker) {
            this.speaker = speaker;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getAttachment() {
            return attachment;
        }

        public void setAttachment(String attachment) {
            this.attachment = attachment;
        }
    }
}
