package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;
import www.dico.cn.partybuild.persistance.CreditInfoBean;

public interface CreditInfoView extends BaseMvpView {
    void resultSuccess(CreditInfoBean result);

    void resultFailure(String result);
}
