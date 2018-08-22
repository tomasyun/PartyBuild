package www.dico.cn.partybuild.presenter;

import android.app.Activity;
import android.app.Dialog;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.activity.TaskBriefActivity;
import www.dico.cn.partybuild.modleview.TaskBriefView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class TaskBriefPresenter extends BaseMvpPresenter<TaskBriefView> {
    Activity activity = AppManager.getManager().findActivity(TaskBriefActivity.class);
    //任务摘要
    IProgressDialog dialog = new IProgressDialog() {
        @Override
        public Dialog getDialog() {
            LoadingDialog.Builder builder = new LoadingDialog.Builder(activity)
                    .setCancelable(true)
                    .setCancelOutside(true)
                    .setMessage("加载中..")
                    .setShowMessage(true);
            return builder.create();
        }
    };

    public void doTaskBriefRequest(String id) {
        EasyHttp.post("studyTaskInfo")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("id", id)
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

    //校验是否可以添加学习成果
    public void verifyOpenStudyResult(String id) {
        EasyHttp.post("isCompleteTask")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("id", id)
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
                    @Override
                    public void onSuccess(String s) {
                        getMvpView().verifySuccess(s);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        getMvpView().verifyFailure(e.getMessage());
                    }
                });
    }
}
