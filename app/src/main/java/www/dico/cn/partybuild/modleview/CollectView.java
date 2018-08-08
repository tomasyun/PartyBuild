package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;
import www.dico.cn.partybuild.bean.CollectsBean;

public interface CollectView extends BaseMvpView {
    void resultSuccess(String result);

    void resultFailure(String result);
}
