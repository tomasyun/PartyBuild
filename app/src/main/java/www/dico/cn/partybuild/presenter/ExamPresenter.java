package www.dico.cn.partybuild.presenter;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.modleview.ExamView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class ExamPresenter extends BaseMvpPresenter<ExamView> {
    //待考
    public void examsOnRequest(IProgressDialog dialog, String type) {
        EasyHttp.post("examList")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("type", type)
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
                    @Override
                    public void onSuccess(String result) {
                        getMvpView().examOnResultSuccess(result);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        if (e.getCode() == ApiException.ERROR.NETWORD_ERROR)
                            getMvpView().netWorkUnAvailable();
                        else
                            getMvpView().examOnResultFailure(e.getMessage());
                    }
                });
    }

    //已考
    public void examsOkRequest(IProgressDialog dialog, String type) {
        EasyHttp.post("examList")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("type", type)
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
                    @Override
                    public void onSuccess(String result) {
                        getMvpView().examOkResultSuccess(result);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        if (e.getCode() == ApiException.ERROR.NETWORD_ERROR)
                            getMvpView().netWorkUnAvailable();
                        else
                            getMvpView().examOkResultFailure(e.getMessage());
                    }
                });
    }
}
