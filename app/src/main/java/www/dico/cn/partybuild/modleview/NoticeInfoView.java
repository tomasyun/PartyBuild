package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface NoticeInfoView extends BaseMvpView {
    void resultSuccess(String result);

    void resultFailure(String result);

    void submitCommentSuccess(String result);

    void submitCommentFailure(String result);
}
