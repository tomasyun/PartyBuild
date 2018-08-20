package www.dico.cn.partybuild.presenter;

import android.app.Activity;
import android.app.Dialog;

import www.dico.cn.partybuild.AppConfig;
import www.dico.cn.partybuild.AppManager;
import www.dico.cn.partybuild.activity.MeetingBriefActivity;
import www.dico.cn.partybuild.modleview.MeetingBriefView;
import www.dico.cn.partybuild.mvp.presenter.BaseMvpPresenter;
import www.dico.cn.partybuild.widget.LoadingDialog;
import www.yuntdev.com.library.EasyHttp;
import www.yuntdev.com.library.callback.ProgressDialogCallBack;
import www.yuntdev.com.library.exception.ApiException;
import www.yuntdev.com.library.subsciber.IProgressDialog;

public class MeetingBriefPresenter extends BaseMvpPresenter<MeetingBriefView> {
    Activity activity=AppManager.getManager().findActivity(MeetingBriefActivity.class);
    //会议摘要
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

    public void doMeetingBriefRequest(String id) {
        EasyHttp.post("conferenceBrief")
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

    public void doSignUpRequest(String id) {
        EasyHttp.post("signUp")
                .headers("Authorization", AppConfig.getSpUtils().getString("token"))
                .params("id", id)
                .execute(new ProgressDialogCallBack<String>(dialog, true, true) {
                    @Override
                    public void onSuccess(String s) {
                        getMvpView().signUpResultSuccess(s);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        getMvpView().signUpResultFailure(e.getMessage());
                    }
                });
    }

    public void doLeaveRequest(String id){
        EasyHttp.post("leave")
                .params("id",id)
                .execute(new ProgressDialogCallBack<String>(dialog,true,true) {
                    @Override
                    public void onSuccess(String s) {
                        getMvpView().leaveResultSuccess(s);
                    }

                    @Override
                    public void onError(ApiException e) {
                        super.onError(e);
                        getMvpView().leaveResultFailure(e.getMessage());
                    }
                });
    }
}
