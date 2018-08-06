package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.bean.PreviewQuestionBean;
import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface PreviewQuestionResultView extends BaseMvpView {
    void resultSuccess(PreviewQuestionBean result);

    void resultFailure(String result);
}
