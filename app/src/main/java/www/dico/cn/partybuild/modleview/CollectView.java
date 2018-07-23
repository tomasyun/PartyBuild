package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;
import www.dico.cn.partybuild.persistance.CollectsBean;

public interface CollectView extends BaseMvpView {
    void resultSuccess(CollectsBean result);

    void resultFailure(String result);
}
