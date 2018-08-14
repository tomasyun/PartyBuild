package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface MeetingBriefView extends BaseMvpView {
    void resultSuccess(String result);

    void resultFailure(String result);

    void signUpResultSuccess(String result);

    void signUpResultFailure(String result);

    void leaveResultSuccess(String result);

    void leaveResultFailure(String result);
}
