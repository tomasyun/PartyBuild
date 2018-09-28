package www.dico.cn.partybuild.presenter;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.modleview.OnlineSurveyView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class OnlineSurveyPresenter extends BaseMvpPresenter<OnlineSurveyView> {
    public void doGetSurveyQuestionRequest(IProgressDialog dialog, String id) {
        EasyHttp.post("getQuestionSurvey")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("id", id)
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
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

    public void doSubmitSurveyQuestionAnswerRequest(IProgressDialog dialog, String json) {
        EasyHttp.post("saveQuestionnaireAnswers")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .upJson(json)
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
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
