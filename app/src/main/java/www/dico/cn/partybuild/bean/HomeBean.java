package www.dico.cn.partybuild.bean;

import java.util.List;

public class HomeBean extends BaseProtocol {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private AnnouncementBean announcement;
        private List<AdvertisementBean> advertisement;

        public AnnouncementBean getAnnouncement() {
            return announcement;
        }

        public void setAnnouncement(AnnouncementBean announcement) {
            this.announcement = announcement;
        }

        public List<AdvertisementBean> getAdvertisement() {
            return advertisement;
        }

        public void setAdvertisement(List<AdvertisementBean> advertisement) {
            this.advertisement = advertisement;
        }

        public static class AnnouncementBean {

            private String id;
            private String content;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        public static class AdvertisementBean {

            private String id;
            private String advertImg;
            private String advertTitle;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAdvertImg() {
                return advertImg;
            }

            public void setAdvertImg(String advertImg) {
                this.advertImg = advertImg;
            }

            public String getAdvertTitle() {
                return advertTitle;
            }

            public void setAdvertTitle(String advertTitle) {
                this.advertTitle = advertTitle;
            }
        }
    }
}
