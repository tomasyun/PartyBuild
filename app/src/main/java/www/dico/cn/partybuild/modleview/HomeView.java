package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;
import www.dico.cn.partybuild.bean.HomeBean;

public interface HomeView extends BaseMvpView {
    void resultSuccess(HomeBean result);

    void resultFailure(String result);
}
