package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.bean.HomeBean;
import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface HomeView extends BaseMvpView {
    void resultSuccess(String result);

    void resultFailure(String result);
}
