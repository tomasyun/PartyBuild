package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface QuestionOptionPreviewView extends BaseMvpView {
    void questionPreviewResultSuccess(String result);

    void questionPreviewResultFailure(String result);

    void netWorkUnAvailable();
}
