package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface CommonView extends BaseMvpView{
    void resultSuccess(String result);

    void resultFailure(String result);

    void netWorkUnAvailable();

    void getNoticeSuccess(String result);

    void getNoticeFailure(String result);
}
