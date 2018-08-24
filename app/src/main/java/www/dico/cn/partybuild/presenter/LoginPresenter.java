package www.dico.cn.partybuild.presenter;

import android.app.Dialog;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.activity.LoginActivity;
import www.dico.cn.partybuild.modleview.LoginView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class LoginPresenter extends BaseMvpPresenter<LoginView> {
    public IProgressDialog getDialog() {
        IProgressDialog dialog = new IProgressDialog() {
            @Override
            public Dialog getDialog() {
                LoadingDialog.Builder builder = new LoadingDialog.Builder(AppManager.getManager().findActivity(LoginActivity.class))
                        .setCancelable(true)
                        .setCancelOutside(true)
                        .setMessage("获取中..")
                        .setShowMessage(true);
                return builder.create();
            }
        };
        return dialog;
    }

    public void clickRequest(String name, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("username", name);
        map.put("password", password);
        String params = new Gson().toJson(map);
        EasyHttp.post("auth/mobileLogin")
                .upJson(params)
                .execute(new ProgressDialogCallBack<String>(getDialog(), true, true) {
                    @Override
                    public void onSuccess(String result) {
                        getMvpView().resultSuccess(result);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        if (e.getCode() == ApiException.ERROR.NETWORD_ERROR)
                            getMvpView().netWorkUnAvailable();
                        else
                            getMvpView().resultFailure(e.getMessage());
                    }
                });
    }
}
