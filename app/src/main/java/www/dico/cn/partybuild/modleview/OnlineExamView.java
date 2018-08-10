package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface OnlineExamView extends BaseMvpView {
    void resultSuccess(String result);

    void resultFailure(String result);

    void submitSuccess(String result);

    void submitFailure(String result);
}
