package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.bean.MailboxListBean;
import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface MailboxListView extends BaseMvpView {
    void resultSuccess(MailboxListBean result);

    void resultFailure(String result);
}
