package www.dico.cn.partybuild.bean;

public class VersionBean extends BaseProtocol {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String id;
        private String newUrl;
        private String versionCode;
        private String content;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getNewUrl() {
            return newUrl;
        }

        public void setNewUrl(String newUrl) {
            this.newUrl = newUrl;
        }

        public String getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(String versionCode) {
            this.versionCode = versionCode;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
