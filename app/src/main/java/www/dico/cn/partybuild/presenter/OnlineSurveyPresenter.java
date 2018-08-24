package www.dico.cn.partybuild.presenter;

import android.app.Dialog;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.activity.OnlineSurveyActivity;
import www.dico.cn.partybuild.modleview.OnlineSurveyView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class OnlineSurveyPresenter extends BaseMvpPresenter<OnlineSurveyView> {
    public IProgressDialog getDialog() {
        IProgressDialog dialog = new IProgressDialog() {
            @Override
            public Dialog getDialog() {
                LoadingDialog.Builder builder = new LoadingDialog.Builder(AppManager.getManager().findActivity(OnlineSurveyActivity.class))
                        .setCancelable(true)
                        .setCancelOutside(true)
                        .setMessage("获取中..")
                        .setShowMessage(true);
                return builder.create();
            }
        };
        return dialog;
    }

    public void doGetSurveyQuestionRequest(String id) {
        EasyHttp.post("getQuestionSurvey")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("id", id)
                .execute(new ProgressDialogCallBack<String>(getDialog(), true, true) {
                    @Override
                    public void onSuccess(String s) {
                        getMvpView().resultSuccess(s);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        getMvpView().resultFailure(e.getMessage());
                    }
                });
    }

    public void doSubmitSurveyQuestionAnswerRequest(String json) {
        EasyHttp.post("saveQuestionnaireAnswers")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .upJson(json)
                .execute(new ProgressDialogCallBack<String>(getDialog(), true, true) {
                    @Override
                    public void onSuccess(String s) {
                        getMvpView().saveAnswerSuccess(s);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        getMvpView().saveAnswerFailure(e.getMessage());
                    }
                });

    }
}
