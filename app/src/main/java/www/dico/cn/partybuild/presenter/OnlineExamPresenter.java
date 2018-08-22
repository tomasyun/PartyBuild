package www.dico.cn.partybuild.presenter;

import android.app.Activity;
import android.app.Dialog;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.activity.OnlineExamActivity;
import www.dico.cn.partybuild.modleview.OnlineExamView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class OnlineExamPresenter extends BaseMvpPresenter<OnlineExamView> {
    Activity activity = AppManager.getManager().findActivity(OnlineExamActivity.class);
    //获取试卷试题
    IProgressDialog dialog = new IProgressDialog() {
        @Override
        public Dialog getDialog() {
            LoadingDialog.Builder builder = new LoadingDialog.Builder(activity)
                    .setCancelable(true)
                    .setCancelOutside(true)
                    .setMessage("获取中..")
                    .setShowMessage(true);
            return builder.create();
        }
    };

    public void onlineExamRequest(String examId) {
        EasyHttp.post("queryExamQuestionList")
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

    //提交答案
    public void doSaveExamAnswer(String answer) {
        EasyHttp.post("saveExamAnswers")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .upJson(answer)
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
                    @Override
                    public void onSuccess(String s) {
                        getMvpView().submitSuccess(s);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        getMvpView().submitFailure(e.getMessage());
                    }
                });
    }
}
