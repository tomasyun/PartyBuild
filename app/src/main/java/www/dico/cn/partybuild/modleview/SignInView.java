package www.dico.cn.partybuild.modleview;

import www.dico.cn.partybuild.mvp.view.BaseMvpView;

public interface SignInView extends BaseMvpView {
    void resultSuccess(String result);

    void resultFailure(String result);

    void saveSignInSuccess(String result);

    void saveSignInFailure(String result);

    void netWorkUnAvailable();

    void signUpSuccessRefresh();
}
