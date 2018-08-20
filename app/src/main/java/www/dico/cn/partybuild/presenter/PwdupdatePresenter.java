package www.dico.cn.partybuild.presenter;

import android.app.Activity;
import android.app.Dialog;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.activity.PwdupdateActivity;
import www.dico.cn.partybuild.modleview.PwdupdateView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class PwdupdatePresenter extends BaseMvpPresenter<PwdupdateView> {
    Activity activity=AppManager.getManager().findActivity(PwdupdateActivity.class);
    IProgressDialog dialog = new IProgressDialog() {
        @Override
        public Dialog getDialog() {
            LoadingDialog.Builder builder = new LoadingDialog.Builder(activity)
                    .setCancelable(true)
                    .setCancelOutside(true)
                    .setMessage("修改中..")
                    .setShowMessage(true);
            return builder.create();
        }
    };

    public void pwdupdateRequest(String oldPassword, String newPassword) {
        EasyHttp.post("updatePassword")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("oldPassword", oldPassword)
                .params("newPassword", newPassword)
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
                    @Override
                    public void onSuccess(String result) {
                        getMvpView().resultSuccess(result);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        getMvpView().resultFailure(e.getMessage());
                    }
                });
    }
}
