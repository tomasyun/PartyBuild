package www.dico.cn.partybuild.bean;

import java.util.List;

public class InfodetailBean extends BaseProtocol {
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
        private String publicUnit;
        private String publishDate;
        private String content;
        private String attachment;
        private List<CommitListBean> commitList;

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

        public String getPublicUnit() {
            return publicUnit;
        }

        public void setPublicUnit(String publicUnit) {
            this.publicUnit = publicUnit;
        }

        public String getPublishDate() {
            return publishDate;
        }

        public void setPublishDate(String publishDate) {
            this.publishDate = publishDate;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAttachment() {
            return attachment;
        }

        public void setAttachment(String attachment) {
            this.attachment = attachment;
        }

        public List<CommitListBean> getCommitList() {
            return commitList;
        }

        public void setCommitList(List<CommitListBean> commitList) {
            this.commitList = commitList;
        }

        public static class CommitListBean {
            private String id;
            private String name;
            private String avatar;
            private String commitContent;
            private String commitDate;

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

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getCommitContent() {
                return commitContent;
            }

            public void setCommitContent(String commitContent) {
                this.commitContent = commitContent;
            }

            public String getCommitDate() {
                return commitDate;
            }

            public void setCommitDate(String commitDate) {
                this.commitDate = commitDate;
            }
        }
    }
}
