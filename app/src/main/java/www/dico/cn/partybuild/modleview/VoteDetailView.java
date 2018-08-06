package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.bean.VoteDetailBean;
import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface VoteDetailView extends BaseMvpView {
    void resultSuccess(VoteDetailBean result);

    void resultFailure(String result);
}
