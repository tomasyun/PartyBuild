package www.dico.cn.partybuild.presenter;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.modleview.CourseInfoView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class CourseInfoPresenter extends BaseMvpPresenter<CourseInfoView> {
    //课件详情
    public void doGetIntoCourseInfoRequest(IProgressDialog dialog, String id, String taskId, String flag) {
        EasyHttp.post("courseInfo")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("id", id)
                .params("taskId", taskId)
                .params("flag", flag)
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
                    @Override
                    public void onSuccess(String result) {
                        getMvpView().intoResultSuccess(result);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        if (e.getCode() == ApiException.ERROR.NETWORD_ERROR)
                            getMvpView().netWorkUnAvailable();
                        else
                            getMvpView().intoResultFailure(e.getMessage());
                    }
                });
    }

    public void doGetOutCourseInfoRequest(IProgressDialog dialog, String id, String taskId, String flag) {
        EasyHttp.post("courseInfo")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("id", id)
                .params("taskId", taskId)
                .params("flag", flag)
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
                    @Override
                    public void onSuccess(String result) {
                        getMvpView().outResultSuccess(result);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        if (e.getCode() == ApiException.ERROR.NETWORD_ERROR)
                            getMvpView().netWorkUnAvailable();
                        else
                            getMvpView().outResultFailure(e.getMessage());
                    }
                });
    }
}
