package www.dico.cn.partybuild.presenter;

import android.app.Dialog;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.modleview.LoginView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.bean.LoginBean;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class LoginPresenter extends BaseMvpPresenter<LoginView> {
    IProgressDialog dialog = new IProgressDialog() {
        @Override
        public Dialog getDialog() {
            LoadingDialog.Builder builder = new LoadingDialog.Builder(AppManager.getManager().curActivity())
                    .setCancelable(true)
                    .setCancelOutside(true)
                    .setMessage("登录中..")
                    .setShowMessage(true);
            return builder.create();
        }
    };

    public void clickRequest(String name, String password) {
        disposable = EasyHttp.post("mobile/loginInterface/login")
                .params("username", name)
                .params("password", password)
                .execute(new ProgressDialogCallBack<LoginBean>(dialog, true, true) {
                    @Override
                    public void onSuccess(LoginBean loginBean) {
                        getMvpView().resultSuccess(loginBean);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        getMvpView().resultFailure(e.getMessage());
                    }
                });
    }

    @Override
    public void onDestroyPresenter() {
        super.onDestroyPresenter();
        if (null!=disposable&&disposable.isDisposed())
            disposable.dispose();
    }
}
