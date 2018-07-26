package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;
import www.dico.cn.partybuild.bean.PwdupdateBean;

public interface PwdupdateView extends BaseMvpView {
    void resultSuccess(PwdupdateBean result);

    void resultFailure(String result);
}
