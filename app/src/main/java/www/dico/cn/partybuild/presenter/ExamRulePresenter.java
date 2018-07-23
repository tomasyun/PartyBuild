package www.dico.cn.partybuild.presenter;

import android.app.Dialog;

import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.modleview.ExamRuleView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.persistance.CreditRankBean;
import www.dico.cn.partybuild.persistance.ExamRuleBean;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class ExamRulePresenter extends BaseMvpPresenter<ExamRuleView> {

    public void examRuleRequest(String examId) {
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
                .params("examId", examId)
                .execute(new ProgressDialogCallBack<ExamRuleBean>(dialog, true, true) {
                    @Override
                    public void onSuccess(ExamRuleBean examRuleBean) {

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
