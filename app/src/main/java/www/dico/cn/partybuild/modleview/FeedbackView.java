package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;
import www.dico.cn.partybuild.persistance.FeedBackBean;

public interface FeedbackView extends BaseMvpView{
    void resultSuccess(FeedBackBean result);

    void resultFailure(String result);
}
