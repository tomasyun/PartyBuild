package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;
import www.dico.cn.partybuild.bean.BaseInfoBean;

public interface BaseInfoView extends BaseMvpView {
    void resultSuccess(BaseInfoBean result);

    void resultFailure(String result);
}
