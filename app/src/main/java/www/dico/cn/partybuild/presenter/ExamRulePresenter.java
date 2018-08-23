package www.dico.cn.partybuild.presenter;

import android.app.Activity;
import android.app.Dialog;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.activity.ExamRuleActivity;
import www.dico.cn.partybuild.modleview.ExamRuleView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class ExamRulePresenter extends BaseMvpPresenter<ExamRuleView> {
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

    public void examRuleRequest(String examId) {
        EasyHttp.post("examRule")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("id", examId)
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
