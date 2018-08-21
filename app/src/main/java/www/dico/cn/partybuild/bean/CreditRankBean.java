package www.dico.cn.partybuild.bean;

import java.util.List;

public class CreditRankBean extends BaseProtocol {
    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String id;
        private String mscore;
        private String mrank;
        private String mavatar;
        private List<CreditInfoListBean> creditInfoList;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMscore() {
            return mscore;
        }

        public void setMscore(String mscore) {
            this.mscore = mscore;
        }

        public String getMrank() {
            return mrank;
        }

        public void setMrank(String mrank) {
            this.mrank = mrank;
        }

        public String getMavatar() {
            return mavatar;
        }

        public void setMavatar(String mavatar) {
            this.mavatar = mavatar;
        }

        public List<CreditInfoListBean> getCreditInfoList() {
            return creditInfoList;
        }

        public void setCreditInfoList(List<CreditInfoListBean> creditInfoList) {
            this.creditInfoList = creditInfoList;
        }

        public static class CreditInfoListBean {

            private String id;
            private String rank;
            private String avatar;
            private String name;
            private String score;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRank() {
                return rank;
            }

            public void setRank(String rank) {
                this.rank = rank;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }
        }
    }
}
