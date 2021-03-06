package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface VoteDetailView extends BaseMvpView {
    void resultSuccess(String result);

    void resultFailure(String result);

    void submitVoteResultSuccess(String result);

    void submitVoteResultFailure(String result);

    void netWorkUnAvailable();
}
