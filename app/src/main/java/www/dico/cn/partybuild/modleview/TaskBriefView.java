package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface TaskBriefView extends BaseMvpView {
    void resultSuccess(String result);

    void resultFailure(String result);

    void verifySuccess(String result);

    void verifyFailure(String result);

    void netWorkUnAvailable();
}
