package www.dico.cn.partybuild.bean;

import java.util.List;

public class OrgActBriefBean extends BaseProtocol {

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {

        private String id;
        private String theme;
        private String themeImg;
        private String address;
        private String startDate;
        private String speaker;
        private String category;
        private String brief;
        private String attendNum;
        private String conferenceState;
        private String signUpState;
        private String leaveState;
        private List<String> attender;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTheme() {
            return theme;
        }

        public void setTheme(String theme) {
            this.theme = theme;
        }

        public String getThemeImg() {
            return themeImg;
        }

        public void setThemeImg(String themeImg) {
            this.themeImg = themeImg;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getSpeaker() {
            return speaker;
        }

        public void setSpeaker(String speaker) {
            this.speaker = speaker;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getBrief() {
            return brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        public String getAttendNum() {
            return attendNum;
        }

        public void setAttendNum(String attendNum) {
            this.attendNum = attendNum;
        }

        public String getConferenceState() {
            return conferenceState;
        }

        public void setConferenceState(String conferenceState) {
            this.conferenceState = conferenceState;
        }

        public String getSignUpState() {
            return signUpState;
        }

        public void setSignUpState(String signUpState) {
            this.signUpState = signUpState;
        }

        public String getLeaveState() {
            return leaveState;
        }

        public void setLeaveState(String leaveState) {
            this.leaveState = leaveState;
        }

        public List<String> getAttender() {
            return attender;
        }

        public void setAttender(List<String> attender) {
            this.attender = attender;
        }
    }
}
