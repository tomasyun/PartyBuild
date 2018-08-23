package www.dico.cn.partybuild.presenter;

import android.app.Activity;
import android.app.Dialog;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.activity.CourseInfoActivity;
import www.dico.cn.partybuild.modleview.CourseInfoView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class CourseInfoPresenter extends BaseMvpPresenter<CourseInfoView> {
    //课件详情
    IProgressDialog dialog = new IProgressDialog() {
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

    public void doGetIntoCourseInfoRequest(String id, String taskId, String flag) {
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
                        getMvpView().intoResultFailure(e.getMessage());
                    }
                });
    }

    public void doGetOutCourseInfoRequest(String id, String taskId, String flag) {
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
                        getMvpView().outResultFailure(e.getMessage());
                    }
                });
    }
}
