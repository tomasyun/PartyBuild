package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.bean.BaseProtocol;
import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface LeaveReasonView extends BaseMvpView {
    void resultSuccess(BaseProtocol result);

    void resultFailure(String result);
}
