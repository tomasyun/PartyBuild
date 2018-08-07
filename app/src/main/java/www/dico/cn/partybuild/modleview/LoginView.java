package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;
import www.dico.cn.partybuild.bean.LoginBean;

public interface LoginView extends BaseMvpView {
    void resultSuccess(String result);
    void resultFailure(String result);
}
