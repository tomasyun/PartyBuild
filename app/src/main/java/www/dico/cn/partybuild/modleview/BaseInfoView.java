package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface BaseInfoView extends BaseMvpView {
    void resultSuccess(String result);

    void resultFailure(String result);
}
