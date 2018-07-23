package www.dico.cn.partybuild.presenter;

import android.app.Dialog;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.modleview.FeedbackView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.persistance.FeedBackBean;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class FeedbackPresenter extends BaseMvpPresenter<FeedbackView> {

    public void feedBackSubmit(String id, String content) {
        IProgressDialog dialog = new IProgressDialog() {
            @Override
            public Dialog getDialog() {
                LoadingDialog.Builder builder = new LoadingDialog.Builder(AppManager.getManager().curActivity())
                        .setCancelable(true)
                        .setCancelOutside(true)
                        .setMessage("提交中..")
                        .setShowMessage(true);
                return builder.create();
            }
        };
        disposable = EasyHttp.post("")
                .params("id", id)
                .params("content", content)
                .execute(new ProgressDialogCallBack<FeedBackBean>(dialog, true, true) {
                    @Override
                    public void onSuccess(FeedBackBean feedBackBean) {

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
