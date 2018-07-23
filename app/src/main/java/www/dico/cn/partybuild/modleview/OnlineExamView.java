package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;
import www.dico.cn.partybuild.persistance.OnlineExamBean;

public interface OnlineExamView extends BaseMvpView {
    void resultSuccess(OnlineExamBean result);

    void resultFailure(String result);
}
