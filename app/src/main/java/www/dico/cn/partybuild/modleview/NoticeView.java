package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;
import www.dico.cn.partybuild.persistance.NoticeBean;

public interface NoticeView extends BaseMvpView {
    void resultSuccess(NoticeBean result);

    void resultFailure(String result);
}
