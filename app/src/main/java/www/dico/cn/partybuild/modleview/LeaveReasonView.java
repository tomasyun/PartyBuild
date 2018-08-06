package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.bean.LeaveBean;
import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface LeaveReasonView extends BaseMvpView {
    void resultSuccess(LeaveBean result);

    void resultFailure(String result);
}
