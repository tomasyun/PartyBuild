package www.dico.cn.partybuild.presenter;

import android.app.Activity;
import android.app.Dialog;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.activity.CollectActivity;
import www.dico.cn.partybuild.modleview.CollectView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class CollectPresenter extends BaseMvpPresenter<CollectView> {
    Activity activity=AppManager.getManager().findActivity(CollectActivity.class);
    IProgressDialog dialog = new IProgressDialog() {
        @Override
        public Dialog getDialog() {
            LoadingDialog.Builder builder = new LoadingDialog.Builder(activity)
                    .setCancelable(true)
                    .setCancelOutside(true)
                    .setMessage("请求中..")
                    .setShowMessage(true);
            return builder.create();
        }
    };

    public void collectsRequest(String id) {
        EasyHttp.post("")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("id", id)
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
