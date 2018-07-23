package www.dico.cn.partybuild.presenter;

import android.app.Dialog;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.modleview.PwdupdateView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.persistance.PwdupdateBean;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class PwdupdatePresenter extends BaseMvpPresenter<PwdupdateView> {

    public void pwdupdateRequest(String oldPassword, String newPassword, String againPassword) {
        IProgressDialog dialog = new IProgressDialog() {
            @Override
            public Dialog getDialog() {
                LoadingDialog.Builder builder = new LoadingDialog.Builder(AppManager.getManager().curActivity())
                        .setCancelable(true)
                        .setCancelOutside(true)
                        .setMessage("修改中..")
                        .setShowMessage(true);
                return builder.create();
            }
        };
        disposable = EasyHttp.post("")
                .params("oldPassword", oldPassword)
                .params("newPassword", newPassword)
                .params("againPassword", againPassword)
                .execute(new ProgressDialogCallBack<PwdupdateBean>(dialog, true, true) {
                    @Override
                    public void onSuccess(PwdupdateBean pwdupdateBean) {

                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
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
