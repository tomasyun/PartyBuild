package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface CourseInfoView extends BaseMvpView {
    void intoResultSuccess(String result);

    void intoResultFailure(String result);

    void outResultSuccess(String result);

    void outResultFailure(String result);

    void netWorkUnAvailable();
}
