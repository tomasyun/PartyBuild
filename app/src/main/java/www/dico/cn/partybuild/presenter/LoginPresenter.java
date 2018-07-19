package www.dico.cn.partybuild.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.modleview.LoginView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.persistance.LoginBean;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.SimpleCallBack;
import www.yuntdev.com.library.exception.ApiException;

public class LoginPresenter extends BaseMvpPresenter<LoginView> {

    public void clickRequest(String name, String password) {
        EasyHttp.post("mobile/loginInterface/login")
                .params("username", name)
                .params("password", password)
                .execute(new SimpleCallBack<LoginBean>() {
                    @Override
                    public void onError(ApiException e) {
                        getMvpView().resultFailure(e.getMessage());
                    }

                    @Override
                    public void onSuccess(LoginBean loginBean) {
                        getMvpView().resultSuccess(loginBean);
                    }
                });
    }

    @Override
    public void onCreatePresenter(@Nullable Bundle savedState) {
        super.onCreatePresenter(savedState);
        if (savedState != null) {
            Log.e("perfect-mvp", "RequestPresenter5  onCreatePersenter 测试  = " + savedState.getString("test2"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("perfect-mvp", "RequestPresenter5  onSaveInstanceState 测试 ");
        outState.putString("test2", "test_save2");
    }

    @Override
    public void onDestroyPresenter() {
        super.onDestroyPresenter();
    }
}
