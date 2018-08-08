package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.bean.NoticeInfoBean;
import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface NoticeInfoView extends BaseMvpView{
    void resultSuccess(String result);

    void resultFailure(String result);
}
