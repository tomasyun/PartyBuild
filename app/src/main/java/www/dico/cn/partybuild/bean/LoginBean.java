package www.dico.cn.partybuild.bean;

public class LoginBean {

    /**
     * code : 0000
     * msg : 登录成功
     * data : {"name":"Admin","isManager":true,"avatar":"/img/headIcon/c1d134b117d14957bb3f6f4009b27de3.jpg","partyPost":"党委书记","userId":"1","token":"eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjbXMiLCJzdWIiOiJhZG1pbiIsImF1ZCI6Im1vYmlsZSIsImlhdCI6MTU0MjU5MTQyMCwiZXhwIjoxNTQyNzM1NDIwfQ.iyWiwxCJU6wx8QVycYF_Xw4I6ucMt32dl3fysoiCsV6acFbVyiybXTZnbLibOETQABZWoMZaZVCRN26z2UGouw"}
     */

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

    public static class DataBean {
        /**
         * name : Admin
         * isManager : true
         * avatar : /img/headIcon/c1d134b117d14957bb3f6f4009b27de3.jpg
         * partyPost : 党委书记
         * userId : 1
         * token : eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJjbXMiLCJzdWIiOiJhZG1pbiIsImF1ZCI6Im1vYmlsZSIsImlhdCI6MTU0MjU5MTQyMCwiZXhwIjoxNTQyNzM1NDIwfQ.iyWiwxCJU6wx8QVycYF_Xw4I6ucMt32dl3fysoiCsV6acFbVyiybXTZnbLibOETQABZWoMZaZVCRN26z2UGouw
         */

        private String name;
        private boolean isManager;
        private String avatar;
        private String partyPost;
        private String userId;
        private String token;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean IsManager() {
            return isManager;
        }

        public void setIsManager(boolean isManager) {
            this.isManager = isManager;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPartyPost() {
            return partyPost;
        }

        public void setPartyPost(String partyPost) {
            this.partyPost = partyPost;
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
}
