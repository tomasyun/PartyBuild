package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;
import www.dico.cn.partybuild.persistance.CreditRankBean;

public interface CreditRankView extends BaseMvpView{
    void resultSuccess(CreditRankBean result);

    void resultFailure(String result);
}
