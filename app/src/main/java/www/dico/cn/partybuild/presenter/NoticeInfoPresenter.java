package www.dico.cn.partybuild.presenter;

import android.app.Dialog;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.bean.NoticeInfoBean;
import www.dico.cn.partybuild.modleview.NoticeInfoView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class NoticeInfoPresenter extends BaseMvpPresenter<NoticeInfoView> {
    //通知详情
    public void doNoticeInfoRequest(String id) {
        IProgressDialog dialog = new IProgressDialog() {
            @Override
            public Dialog getDialog() {
                LoadingDialog.Builder builder = new LoadingDialog.Builder(AppManager.getManager().curActivity())
                        .setCancelable(true)
                        .setCancelOutside(true)
                        .setMessage("获取中..")
                        .setShowMessage(true);
                return builder.create();
            }
        };
        disposable = EasyHttp.post("")
                .params("id", id)
                .execute(new ProgressDialogCallBack<NoticeInfoBean>(dialog, true, true) {
                    @Override
                    public void onSuccess(NoticeInfoBean noticeInfoBean) {
                        getMvpView().resultSuccess(noticeInfoBean);
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
        if (!disposable.isDisposed())
            disposable.dispose();
    }
}
