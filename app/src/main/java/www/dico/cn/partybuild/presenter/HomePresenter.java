package www.dico.cn.partybuild.presenter;

import android.app.Dialog;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.modleview.HomeView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.bean.HomeBean;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class HomePresenter extends BaseMvpPresenter<HomeView> {

    public void homeDataRequest() {
        final IProgressDialog dialog = new IProgressDialog() {
            @Override
            public Dialog getDialog() {
                LoadingDialog.Builder builder = new LoadingDialog.Builder(AppManager.getManager().curActivity())
                        .setCancelable(true)
                        .setCancelOutside(true)
                        .setMessage("请求中..")
                        .setShowMessage(true);
                return builder.create();
            }
        };
        disposable = EasyHttp.post("")
                .execute(new ProgressDialogCallBack<HomeBean>(dialog, true, true) {

                    @Override
                    public void onSuccess(HomeBean homeBean) {

                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        getMvpView().resultFailure(e.getMessage());
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        dialog.getDialog().dismiss();
                    }
                });
    }

    @Override
    public void onDestroyPresenter() {
        super.onDestroyPresenter();
        if (null != disposable && disposable.isDisposed())
            disposable.dispose();
    }
}
