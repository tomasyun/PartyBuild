package www.dico.cn.partybuild.bean;

public class LoginBean{

    private String code;
    private String msg;
    private DataBean data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean{

        private String partyBranchPost;
        private boolean isManager;
        private String position;
        private String avatar;
        private String userId;
        private String token;

        public String getPartyBranchPost() {
            return partyBranchPost;
        }

        public void setPartyBranchPost(String partyBranchPost) {
            this.partyBranchPost = partyBranchPost;
        }

        public boolean isManager() {
            return isManager;
        }

        public void setManager(boolean manager) {
            isManager = manager;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
