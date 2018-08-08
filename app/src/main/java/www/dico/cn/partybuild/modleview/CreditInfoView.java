package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;
import www.dico.cn.partybuild.bean.CreditInfoBean;

public interface CreditInfoView extends BaseMvpView {
    void resultSuccess(String result);

    void resultFailure(String result);
}
